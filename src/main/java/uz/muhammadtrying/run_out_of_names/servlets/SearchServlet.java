package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.List;

import static uz.muhammadtrying.run_out_of_names.config.MyListener.entityManager;

@WebServlet(name = "search", value = "/student/search")
public class SearchServlet extends HttpServlet {
    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        List<Student> students = studentRepo.findAll();

        List<Student> searchedStudents = students.stream().filter(item -> item.getFirstName().contains(search) || item.getLastName().contains(search)).toList();

        HttpSession session = req.getSession();
        session.setAttribute("students",searchedStudents);
        resp.sendRedirect("/");
    }
}
