package uz.muhammadtrying.run_out_of_names.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.muhammadtrying.run_out_of_names.entity.Group;
import uz.muhammadtrying.run_out_of_names.repos.GroupRepo;

import java.io.IOException;

@WebServlet(name = "group servlet", value = "/group/servlet")
public class GroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cameFrom = req.getParameter("cameFrom");
        GroupRepo groupRepo = new GroupRepo();
        if (cameFrom.equals("/group_create.jsp")) {
            String groupName = req.getParameter("groupName");
            Group group = Group.builder()
                    .name(groupName)
                    .build();
            groupRepo.save(group);
            resp.sendRedirect("http://localhost:8080/groupcrud.jsp");
        } else if (cameFrom.equals("/group_update.jsp")) {
            Integer groupId = Integer.parseInt(req.getParameter("groupId"));
            String groupName = req.getParameter("groupName");
            Group group = groupRepo.findById(groupId);
            group.setName(groupName);
            resp.sendRedirect("http://localhost:8080/groupcrud.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer groupId = Integer.parseInt(req.getParameter("groupId"));
        GroupRepo groupRepo = new GroupRepo();
        Group group = groupRepo.findById(groupId);
        groupRepo.deleteById(group.getId());
        resp.sendRedirect("http://localhost:8080/group_delete.jsp");
    }
}
