package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "student servlet", value = "/student/servlet")
@MultipartConfig
public class StudentServlet extends HttpServlet {

    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cameFrom = req.getParameter("cameFrom");
        if (cameFrom.equals("/index.jsp")) {
            String search = req.getParameter("search");

            List<Student> students = studentRepo.findAll();

            List<Student> searchedStudents = students.stream()
                    .filter(item -> item.getFirstName().toLowerCase().contains(search.toLowerCase()) || item.getLastName()
                            .contains(search)).toList();

            HttpSession session = req.getSession();
            session.setAttribute("students", searchedStudents);
            session.setAttribute("search", search);
        }
        resp.sendRedirect(cameFrom);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getParameter("http://localhost:8080/studentcrud.jsp"));
    }
}
