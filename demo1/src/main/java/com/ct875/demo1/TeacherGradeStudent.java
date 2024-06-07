/**
 * this is a servlet handling TeacherGradeStudent post action requested by studentsOfTheCourse jsp
 */
package com.ct875.demo1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/TeacherGradeStudent")
public class TeacherGradeStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get parameters from form in jsp
        String studentId = request.getParameter("studentId");
        String courseId = request.getParameter("courseId");
        double quizGrade = Double.parseDouble(request.getParameter("quizGrade"));
        double assignmentGrade = Double.parseDouble(request.getParameter("assignmentGrade"));
        double finalExamGrade = Double.parseDouble(request.getParameter("finalExamGrade"));

        //save the grades posted into database
        boolean success = DataHandler.saveStudentGrade(studentId, courseId, quizGrade, assignmentGrade, finalExamGrade);

        if (success) {
            //if successful, dispatch to TeacherGetStudents servlet
            request.getRequestDispatcher("TeacherGetStudents").forward(request, response);
        } else {
            //if failed, dispatch to error page
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
