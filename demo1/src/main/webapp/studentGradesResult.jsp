<%--
this is a page showing grades info of selected course
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Grades Result</title>
</head>
<body>
<h1>Student Grades for Course ${courseId}</h1>

<table>
    <thead>
    <tr>
        <th>Quiz</th>
        <th>Assignment</th>
        <th>Final Exam</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <!--display the assessments content-->
        <td>${assessments[0]}</td>
        <td>${assessments[1]}</td>
        <td>${assessments[2]}</td>
    </tr>
    </tbody>
</table>

</body>
</html>

