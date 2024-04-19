package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "auth", value = "/auth/login")
public class AuthServlet extends HttpServlet {
    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        Optional<Student> optionalStudent = studentRepo.findByUsername(username);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (student.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", student);

                if (Objects.equals(rememberMe, "on")) {
                    Cookie cookie = new Cookie(
                            "currentUser", student.getId().toString()
                    );

                    cookie.setPath("/");
                    cookie.setSecure(false);
                    cookie.setMaxAge(60 * 60);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect("/");
                return;
            }
        }
        resp.sendRedirect("/login.jsp");
    }
}
