<%@ page import="uz.muhammadtrying.run_out_of_names.repos.GroupRepo" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Group" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Student" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.repos.StudentRepo" %><%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 19/04/24
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Updating Student</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>
<%
    GroupRepo groupRepo = new GroupRepo();
    List<Group> groups = groupRepo.findAll();
    Integer studentId = Integer.parseInt(request.getParameter("studentId"));
    StudentRepo studentRepo = new StudentRepo();
    Student student = studentRepo.findById(studentId);
%>
<div class="container">
    <h1 class="mt-4">Update User</h1>
    <form action="student/servlet" method="post">
        <input type="hidden" value="/student_update.jsp">
        <input name="cameFrom" type="hidden" value="http://localhost:8080/student_create.jsp">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input value="<%=student.getFirstName()%>" type="text" class="form-control" id="firstName" name="firstName">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input value="<%=student.getLastName()%>" type="text" class="form-control" id="lastName" name="lastName">
        </div>
        <div class="form-group">
            <label for="group">Group:</label>
            <select class="form-control" id="group" name="groupId">
                <%
                    for (Group group : groups) {%>
                <option <%
                    if (student.getGroup().getId().equals(group.getId())) {
                %>selected<%
                    }%> value="<%=group.getId()%>"><%=group.getName()%>
                </option>
                <%}%>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update User</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
