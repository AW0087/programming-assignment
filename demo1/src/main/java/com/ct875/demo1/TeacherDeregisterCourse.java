/**
 * this is a servlet handling TeacherDeregisterCourse post request from teacherDashboard jsp
 */
package com.ct875.demo1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/TeacherDeregisterCourse")
public class TeacherDeregisterCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get username of the user
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //get courseId
        String courseId = request.getParameter("courseId");

        //call the method of DataHandler to operate deregister action in database
        boolean success = DataHandler.teacherDeregisterCourse(courseId, username);

        if (success) {
            //if successful, redirect to TeacherDashboard servlet to refresh page after data changed
            response.sendRedirect("TeacherDashboard");
        } else {
            //if failed, dispatch to error page
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
