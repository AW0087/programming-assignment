/**
 * this is a servlet handling StudentUnenrollCourse request action from studentDashboard
 */
package com.ct875.demo1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/StudentUnenrollCourse")
public class StudentUnenrollCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get courseId
        String courseId = request.getParameter("courseId");
        //get username of the user
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (DataHandler.studentUnenrollCourse(courseId, username)) {
            //if record changes successful, redirect to StudentDashboard servlet to refresh info
            response.sendRedirect("StudentDashboard");
        } else {
            //if operation failed, dispatch to error jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
