<%@ page import="java.util.List" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Student" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.repos.StudentRepo" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.repos.GroupRepo" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <style>
        .main-content {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<%
    GroupRepo groupRepo = new GroupRepo();
    List<Group> groups = groupRepo.findAll();
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10 main-content">
            <h1>Delete</h1>
            <div style="font-size: larger"><a href="http://localhost:8080/groupcrud.jsp">HOMEPAGE</a></div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <% for (Group group : groups) { %>
                <tr>
                    <td><%= group.getId() %>
                    </td>
                    <td><%= group.getName() %>
                    </td>
                    <td>
                        <form action="group/servlet" method="get">
                            <input type="hidden" name="groupId" value="<%= group.getId() %>">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
