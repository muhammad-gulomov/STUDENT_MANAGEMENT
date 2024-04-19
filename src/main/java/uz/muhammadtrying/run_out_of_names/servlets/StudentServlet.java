package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.muhammadtrying.run_out_of_names.entity.Group;
import uz.muhammadtrying.run_out_of_names.entity.Student;
import uz.muhammadtrying.run_out_of_names.repos.GroupRepo;
import uz.muhammadtrying.run_out_of_names.repos.StudentRepo;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "student servlet", value = "/student/servlet")
@MultipartConfig
public class StudentServlet extends HttpServlet {

    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer studentId = Integer.parseInt(req.getParameter("studentId"));
        studentRepo.deleteById(studentId);
        resp.sendRedirect("http://localhost:8080/student_delete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cameFrom = req.getParameter("cameFrom");
        GroupRepo groupRepo = new GroupRepo();
        StudentRepo studentRepo = new StudentRepo();
        if (cameFrom.equals("http://localhost:8080/student_create.jsp")) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            Integer companyId = Integer.valueOf(req.getParameter("groupId"));

            Student student = Student.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            Group group = groupRepo.findById(companyId);
            student.setGroup(group);
            studentRepo.save(student);
            resp.sendRedirect("http://localhost:8080/studentcrud.jsp");
        } else if (cameFrom.equals("/index.jsp")) {
            String search = req.getParameter("search");

            List<Student> students = studentRepo.findAll();

            List<Student> searchedStudents = students.stream()
                    .filter(item -> item.getFirstName().toLowerCase().contains(search.toLowerCase()) || item.getLastName()
                            .contains(search)).toList();

            HttpSession session = req.getSession();
            session.setAttribute("students", searchedStudents);
            session.setAttribute("search", search);
            resp.sendRedirect(cameFrom);
        } else if (cameFrom.equals("/student_update.jsp")) {
            Integer studentId = Integer.parseInt(req.getParameter("studentId"));
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            Integer groupId = Integer.valueOf(req.getParameter("groupId"));
            Student student = studentRepo.findById(studentId);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            Group group = groupRepo.findById(groupId);
            student.setGroup(group);
            resp.sendRedirect("http://localhost:8080/studentcrud.jsp");
        }
    }
}
