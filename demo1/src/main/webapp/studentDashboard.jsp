<%--
this is a student dashboard jsp showing the enrolled courses info of student
in the page, there are 3 actions: StudentUnenrollCourse, StudentViewGrades， GetStudentAvailableCourse
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
</head>
<body>
<h1>Welcome, Student</h1>

<h2>Enrolled Courses</h2>
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
        <!--iterate the studentEnrolledCourses by jstl-->
        <c:forEach var="course" items="${studentEnrolledCourses}">
            <tr>
                <td>${course[0]}</td>
                <td>${course[1]}</td>
                <td>${course[2]}</td>
                <td>
                    <form action="StudentUnenrollCourse" method="post">
                        <input type="hidden" name="courseId" value="${course[0]}">
                        <button type="submit" name="unenroll" value="${course[0]}">Unenroll</button>
                    </form>

                    <form action="StudentViewGrades" method="post">
                        <input type="hidden" name="courseId" value="${course[0]}">
                        <button type="submit" name="StudentviewGrades" value="${course[0]}">View Grades</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<form action="GetStudentAvailableCourse" method="get">
    <input type="hidden" name="username" value="${username}">
    <button type="submit" name="enrollCourse">Available Courses</button>
</form>


</body>
</html>
