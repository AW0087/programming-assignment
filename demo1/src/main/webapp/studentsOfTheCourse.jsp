<%--
this is a page of teacher role which display students list of a course
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students of the Course</title>
</head>
<body>
<h1>Students of the Course</h1>
<table>
    <thead>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Phone</th>
        <th>Quiz</th>
        <th>Assignment</th>
        <th>Final Exam</th>
        <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    <!--iterate the studentsOfCourse-->
    <c:forEach var="student" items="${studentsOfCourse}">
        <tr>
            <td>${student[0]}</td>
            <td>${student[1]}</td>
            <td>${student[2]}</td>
            <td>${student[3]}</td>
            <td>${student[4]}</td>
            <td>${student[5]}</td>
            <td>
                <!--
                if the student is not graded, then display form to collect the marks from teacher
                if the student has been graded, display scores submitted
                -->
                <c:choose>
                    <c:when test="${empty student[3] && empty student[4] && empty student[5]}">
                        <form action="TeacherGradeStudent" method="post">
                            <input type="hidden" name="courseId" value= "${courseId}">
                            <input type="hidden" name="studentId" value="${student[0]}">
                            <input type="text" name="quizGrade" placeholder="Quiz">
                            <input type="text" name="assignmentGrade" placeholder="Assignment">
                            <input type="text" name="finalExamGrade" placeholder="Final Exam">
                            <button type="submit">Submit</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        Scores Submitted
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
