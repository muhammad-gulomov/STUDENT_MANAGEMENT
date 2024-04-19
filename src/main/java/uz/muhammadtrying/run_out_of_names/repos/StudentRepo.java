package uz.muhammadtrying.run_out_of_names.repos;

import uz.muhammadtrying.run_out_of_names.entity.Student;

import java.util.Optional;

public class StudentRepo extends BaseRepo<Student, Integer> {

    public Optional<Student> findByUsername(String username) {
        return findAll().stream().filter(item -> item.getUserName().equals(username)).findFirst();
    }
}
