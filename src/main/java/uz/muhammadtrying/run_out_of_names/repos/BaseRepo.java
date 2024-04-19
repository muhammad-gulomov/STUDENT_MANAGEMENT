package uz.muhammadtrying.run_out_of_names.repos;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static uz.muhammadtrying.run_out_of_names.config.MyListener.entityManager;

public class BaseRepo<T, I> {

    public Class<T> persistenceClass;

    public BaseRepo() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        this.persistenceClass = (Class<T>) paramType.getActualTypeArguments()[0];
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + persistenceClass.getSimpleName(), persistenceClass).getResultList();
    }

    public void deleteById(I id) {
        begin();
        entityManager.remove(entityManager.find(persistenceClass, id));
        commit();
    }

    public void save(T t) {
        begin();
        entityManager.persist(t);
        commit();
    }

    private void begin() {
        entityManager.getTransaction().begin();
    }

    private void commit() {
        entityManager.getTransaction().commit();
    }
}
