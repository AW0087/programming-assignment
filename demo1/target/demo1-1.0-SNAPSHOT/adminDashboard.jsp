<!--this is a dashboard for admin-->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ww010
  Date: 2024/3/27
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        /* style sheet */
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>

</head>
<body>
<h1>Welcome, Admin</h1>

<!-- show all users information-->
<h2>All Users</h2>
<table id="userTable">
    <thead>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Role</th>
    </tr>
    </thead>
    <!-- userList attribute is set by AdminDashboard servlet-->
    <!-- iterate the userList by jstl-->
    <c:forEach var="user" items="${userList}">
        <tr>
            <td><c:out value="${user[0]}" /></td>
            <td><c:out value="${user[1]}" /></td>
            <td><c:out value="${user[2]}" /></td>
            <td><c:out value="${user[3]}" /></td>
            <td><c:out value="${user[4]}" /></td>
            <td><c:out value="${user[5]}" /></td>
        </tr>
    </c:forEach>
</table>

<!--create new user, post the form information to servlet-->
<h2>Create New User</h2>
<form action="createUser" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone"><br>
    <label for="role">Role:</label>
    <select id="role" name="role">
        <option value="student">Student</option>
        <option value="teacher">Teacher</option>
        <option value="admin">Admin</option>
    </select><br>
    <input type="submit" value="Create User">
</form>

<!--show all courses information-->
<h2>All Courses</h2>
<table id="courseTable">
    <thead>
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Semester</th>
    </tr>
    </thead>
    <!-- courseList attribute is set by AdminDashboard servlet-->
    <!-- iterate the courseList by jstl-->
    <c:forEach var="course" items="${courseList}">
        <tr>
            <td><c:out value="${course[0]}" /></td>
            <td><c:out value="${course[1]}" /></td>
            <td><c:out value="${course[2]}" /></td>
        </tr>
    </c:forEach>
</table>

<!--create new course and post to createCourse servlet-->
<h2>Create New Course</h2>
<form action="createCourse" method="post">
    <label for="courseName">Course Name:</label>
    <input type="text" id="courseName" name="courseName" required><br>
    <label for="semester">Semester:</label>
    <input type="text" id="semester" name="semester" required><br>
    <input type="submit" value="Create Course">
</form>

</body>
</html>
