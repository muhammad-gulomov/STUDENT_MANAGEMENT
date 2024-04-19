package uz.muhammadtrying.run_out_of_names.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import uz.muhammadtrying.run_out_of_names.entity.Group;
import uz.muhammadtrying.run_out_of_names.entity.Student;

@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    private static void init() {
        entityManager.getTransaction().begin();
        Group group = Group.builder()
                .name("G35")
                .build();
        entityManager.persist(group);
        Student student = Student.builder()
                .firstName("Muhammad")
                .lastName("G'ulomov")
                .userName("a")
                .password("1")
                .group(group)
                .build();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

//        init();
        ServletContextListener.super.contextInitialized(sce);
    }
}
