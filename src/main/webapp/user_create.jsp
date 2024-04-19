<%@ page import="uz.muhammadtrying.run_out_of_names.repos.GroupRepo" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Group" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 19/04/24
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating user</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>
<%
    GroupRepo groupRepo = new GroupRepo();
    List<Group> groups = groupRepo.findAll();
%>
<div class="container">
    <h1 class="mt-4">Create User</h1>
    <form action="student/servlet" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName">
        </div>
        <div class="form-group">
            <label for="group">Group:</label>
            <select class="form-control" id="group" name="group">
                <option disabled selected>Choose group</option>
                <%
                    for (Group group : groups) {%>
                <option value="<%=group.getId()%>"><%=group.getName()%>
                </option>
                <%}%>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Create User</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
