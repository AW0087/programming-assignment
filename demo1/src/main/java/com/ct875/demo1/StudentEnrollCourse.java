/**
 * this is a servlet handling StudentEnrollCourse action from studentDashboard jsp
 */
package com.ct875.demo1;
import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/StudentEnrollCourse")
public class StudentEnrollCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get courseId from post
        String courseId = request.getParameter("courseId");

        // get the username of login user
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // call DataHandler.enrollCourse() to save record
        boolean enrollmentStatus = DataHandler.studentEnrollCourse(courseId, username);

        if (enrollmentStatus) {
            //if record inserted successful, redirect to StudentDashboard servlet to refresh the data
            response.sendRedirect("StudentDashboard");
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
