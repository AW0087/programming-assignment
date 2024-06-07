<%--
  this is a page dispalying available course of teacher
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Courses</title>
</head>
<body>
<h1>Available Courses</h1>

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
    <!--iterate teacherAvailableCourses -->
    <c:forEach var="course" items="${teacherAvailableCourses}">
        <tr>
            <td>${course[0]}</td>
            <td>${course[1]}</td>
            <td>${course[2]}</td>
            <td>
                <!-- bind form with a post method to TeacherRegisterCourse servlet-->
                <form action="TeacherRegisterCourse" method="post">
                    <!--set a hidden input, saving the parameter of course[0] aka courseId-->
                    <input type="hidden" name="courseId" value="${course[0]}">
                    <button type="submit">Select</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
