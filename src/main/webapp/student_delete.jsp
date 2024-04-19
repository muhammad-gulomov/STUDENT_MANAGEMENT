<%@ page import="java.util.List" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Student" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.repos.StudentRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <style>
        .main-content {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<%
    StudentRepo studentRepo = new StudentRepo();
    List<Student> students = studentRepo.findAll();
%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10 main-content">
            <h1>Delete</h1>
            <div style="font-size: larger"><a href="http://localhost:8080/studentcrud.jsp">HOMEPAGE</a></div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Group</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <% for (Student student : students) { %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getFirstName() %></td>
                    <td><%= student.getLastName() %></td>
                    <td><%= student.getGroup().getName() %></td>
                    <td>
                        <form action="student/servlet" method="get">
                            <input type="hidden" name="studentId" value="<%= student.getId() %>">
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
