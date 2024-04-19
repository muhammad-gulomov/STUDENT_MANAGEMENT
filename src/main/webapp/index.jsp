<%@ page import="java.util.List" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HOMEPAGE</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>
<%
    Object objectStudents = session.getAttribute("students");
    List<Student> students = new ArrayList<>();
    Object objectSearch = session.getAttribute("search");
    if (objectStudents != null) {
        students = (List<Student>) objectStudents;
    }

%>

<div class="container">
    <h1>Homepage</h1>

    <form action="" class="float-right">
        <button type="submit" class="btn btn-primary">Log In</button>
    </form>

    <form action="student/search" class="form-inline mt-3" method="get">
        <div class="form-group">
            <label for="searchInput" class="mr-2">Search:</label>
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
