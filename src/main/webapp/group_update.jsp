<%@ page import="uz.muhammadtrying.run_out_of_names.repos.GroupRepo" %>
<%@ page import="uz.muhammadtrying.run_out_of_names.entity.Group" %><%--
  Created by IntelliJ IDEA.
  User: muhammad
  Date: 19/04/24
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Updating group</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>
<%
    Integer groupId = Integer.parseInt(request.getParameter("groupId"));
    GroupRepo groupRepo = new GroupRepo();
    Group group = groupRepo.findById(groupId);
%>
<div class="container">
    <h1 class="mt-4">Update Group</h1>
    <form action="group/servlet?groupId=<%=group.getId()%>" method="post">
        <input type="hidden" name="cameFrom" value="/group_update.jsp">
        <div class="form-group">
            <label for="groupName">Group Name:</label>
            <input value="<%=group.getName()%>" type="text" class="form-control" id="groupName" name="groupName">
        </div>

        <button type="submit" class="btn btn-primary">Update Group</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
