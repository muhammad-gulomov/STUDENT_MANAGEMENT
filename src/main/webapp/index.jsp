<%@ page import="java.util.List" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Optional" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.repos.StudentRepo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HOMEPAGE</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
    <style>
        .dropdown {
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
</head>
<body>
<%
    StudentRepo studentRepo = new StudentRepo();

    Object objectStudents = session.getAttribute("students");
    Object objectSearch = session.getAttribute("search");

    List<Student> students = new ArrayList<>();
    if (objectStudents != null) {
        students = (List<Student>) objectStudents;
    }

    Student currentUser = null;

    Object obj = session.getAttribute("currentUser");
    if (obj != null) {
        currentUser = (Student) obj;
    }


    Optional<Student> optionalUser = studentRepo.getUserByCookie(request);
    if (optionalUser.isPresent()) {
        currentUser = optionalUser.get();
    }

%>


<div class="container">
    <h1>Homepage</h1>

    <%
        if (currentUser!=null) {
    %>
    <div class="dropdown float-right">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
            <%= currentUser.getFirstName() %>
        </button>
        <div class="dropdown-content">
            <a href="log/out">Log Out</a>
            <a href="admin.jsp">Admin Panel</a>
        </div>
    </div>
    <% } else { %>
    <form action="login.jsp" class="float-right">
        <button type="submit" class="btn btn-primary">Log In</button>
    </form>
    <% } %>


    <form action="student/servlet" class="form-inline mt-3" method="post">
        <div class="form-group">
            <label for="searchInput" class="mr-2">Search:</label>
            <input type="hidden" name="cameFrom" value="/index.jsp">
            <input <%if(objectSearch!=null) %>value="<%=(String)objectSearch%>" <% %> type="text" id="searchInput"
                   name="search" class="form-control mr-2">
        </div>
        <button type="submit" class="btn btn-primary" id="searchBtn">Search</button>
    </form>

    <div class="mt-3">
        <% if (!students.isEmpty()) { %>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Group</th>
            </tr>
            </thead>
            <tbody>
            <% for (Student student : students) { %>
            <tr>
                <td><%= student.getFirstName() %>
                </td>

                <td><%= student.getLastName() %>
                </td>
                <td><%= student.getGroup().getName() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</div>
</body>
</html>
