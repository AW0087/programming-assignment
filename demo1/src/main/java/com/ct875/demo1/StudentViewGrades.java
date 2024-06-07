/**
 * this is a servlet handling StudentViewGrades post request from studentDashboard jsp
 */
package com.ct875.demo1;
import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/StudentViewGrades")
public class StudentViewGrades extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get username of the user
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");

        //get courseId
        String courseId = request.getParameter("courseId");

        //call the getStudentGrades method of DataHandler to retrieve grades info
        Object[] assessments = DataHandler.getStudentGrades(userName, courseId);

        // save the assessments and courseId into request facilitating to use in jsp
        request.setAttribute("assessments", assessments);
        request.setAttribute("courseId", courseId);

        // dispatch to StudentViewGrades.jsp page
        request.getRequestDispatcher("studentGradesResult.jsp").forward(request, response);
    }
}
