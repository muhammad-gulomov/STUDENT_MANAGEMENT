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
            <h1>Student</h1>
            <div style="font-size: larger"><a href="http://localhost:8080/admin.jsp">HOMEPAGE</a>
                <a class="offset-1" style="font-size: large" href="user_create.jsp">Create</a>
                <a class="offset-1" style="font-size: large" href="">Delete</a>
            </div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Group</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Student student : students) {%>
                <tr>
                    <td>
                        <%=student.getFirstName()%>
                    </td>
                    <td>
                        <%=student.getLastName()%>
                    </td>
                    <td>
                        <%=student.getGroup().getName()%>
                    </td>
                    <td>
                        <a href="?userId=<%=student.getId()%>">
                            Update🔄
                        </a>
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
