/**
 * this is a servlet handling get method from studentDashboard.jsp
 */
package com.ct875.demo1;
import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/GetStudentAvailableCourse")
public class GetStudentAvailableCourse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get attribute of session, username
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // call getStudentAvailableCourse method of DataHandler to retrieve student available courses
        List<String[]> studentAvailableCourses = DataHandler.getStudentAvailableCourse(username);

        // save studentAvailableCourses into request attribute facilitating to be used in jsp
        request.setAttribute("studentAvailableCourses", studentAvailableCourses);

        // dispatch request to StudentAvailableCourse.jsp
        request.getRequestDispatcher("studentAvailableCourse.jsp").forward(request, response);
    }
}
