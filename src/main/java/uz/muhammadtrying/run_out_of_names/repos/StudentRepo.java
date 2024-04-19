package uz.muhammadtrying.run_out_of_names.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import uz.muhammadtrying.run_out_of_names.entity.Student;

import java.util.Optional;

import static uz.muhammadtrying.run_out_of_names.config.MyListener.entityManagerFactory;

public class StudentRepo extends BaseRepo<Student, Integer> {

    public Optional<Student> findByUsername(String username) {
        return findAll().stream().filter(item -> item.getUserName().equals(username)).findFirst();
    }

    public Optional<Student> getUserByCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("currentUser")) {
                return findById(Integer.parseInt(cookie.getValue()));
            }
        }
        return Optional.empty();
    }

    private Optional<Student> findById(int id) {
        return findAll().stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public void deleteByGroupId(Integer groupId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("delete from student where group_id = ?", Student.class);
        nativeQuery.setParameter(1, groupId);
    }
}
