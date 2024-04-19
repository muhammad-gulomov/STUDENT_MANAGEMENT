package uz.muhammadtrying.run_out_of_names.repos;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import uz.muhammadtrying.run_out_of_names.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentRepo extends BaseRepo<Student, Integer> {

    public Optional<Student> findByUsername(String username) {
        return findAll().stream().filter(item -> item.getUserName().equals(username)).findFirst();
    }

    public  Optional<Student> getUserByCookie(HttpServletRequest request) {
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

    private  Optional<Student> findById(int id) {
        Optional<Student> first = findAll().stream().filter(item -> {
            return item.getId().equals(id);
        }).findFirst();
        return first;
    }

}
