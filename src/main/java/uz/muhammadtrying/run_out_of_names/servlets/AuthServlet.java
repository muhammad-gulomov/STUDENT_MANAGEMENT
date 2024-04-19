package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "auth", value = "/auth/login")
public class AuthServlet extends HttpServlet {
    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
