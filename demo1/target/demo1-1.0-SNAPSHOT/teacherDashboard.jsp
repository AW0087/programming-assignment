<%--
  this is a page of teache dashboard, displaying the teaching courses info
  this page has 3 actions: TeacherDeregisterCourse, TeacherGetStudents, GetTeacherAvailableCourse
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Dashboard</title>
</head>
<body>
<h1>Welcome, Teacher</h1>

<h2>Registered Courses</h2>
<table>
    <thead>
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Semester</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <!--iterate teacherRegisteredCourses by jstl-->
    <c:forEach var="course" items="${teacherRegisteredCourses}">
        <tr>
            <td>${course[0]}</td>
            <td>${course[1]}</td>
            <td>${course[2]}</td>
            <td>
                <form action="TeacherDeregisterCourse" method="post">
                    <!--has parameter of courseId-->
                    <input type="hidden" name="courseId" value="${course[0]}">
                    <button type="submit" name="deregister" value="${course[0]}">Deregister</button>
                </form>

                <form action="TeacherGetStudents" method="post">
                    <!--has parameter of courseId-->
                    <input type="hidden" name="courseId" value="${course[0]}">
                    <button type="submit" name="TeacherGetStudents" value="${course[0]}">Show Students</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="GetTeacherAvailableCourse" method="get">
    <input type="hidden" name="username" value="${username}">
    <button type="submit" name="GetTeacherAvailableCourse">Select Courses</button>

</form>
</body>
</html>
