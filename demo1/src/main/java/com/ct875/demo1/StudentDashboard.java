/**
 * this is a StudentDashboard servlet, dispatched by LoginServlet
 */
package com.ct875.demo1;
import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/StudentDashboard")
public class StudentDashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve the username attribute which is stored in session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // call the method to get enrolled courses for a student
        List<String[]> studentEnrolledCourses = DataHandler.getStudentEnrolledCourses(username);
        request.setAttribute("studentEnrolledCourses", studentEnrolledCourses);

        request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
    }
}

