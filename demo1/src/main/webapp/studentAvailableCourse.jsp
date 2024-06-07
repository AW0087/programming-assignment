<%--
 this is a studentAvailableCourse jsp, which is a page containg available courses info
  and shown to student role
  in the page, there is a form with StudentEnrollCourse action
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Courses</title>
    <style>
        .course-table th, .course-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        .course-table th {
            background-color: #f2f2f2;
        }

        .course-table tr:hover {
            background-color: #f2f2f2;
        }
    </style>
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
    <c:forEach var="course" items="${studentAvailableCourses}">
        <tr>
            <td>${course[0]}</td>
            <td>${course[1]}</td>
            <td>${course[2]}</td>
            <td>
                <!-- enroll to the course  -->
                <form action="StudentEnrollCourse" method="post">
                    <!--set a hidden input which include parameter of course[0] aka courseId-->
                    <input type="hidden" name="courseId" value="${course[0]}">
                    <button type="submit">Enroll</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
