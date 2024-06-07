/**
 * this is a AdminDashboard servlet, dispatched by LoginServlet
 */
package com.ct875.demo1;
import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // retrieve all users and courses information
        List<String[]> userList = DataHandler.getAllUsers();
        List<String[]> courseList = DataHandler.getAllCourses();

        request.setAttribute("userList", userList);
        request.setAttribute("courseList", courseList);

        // dispatch to adminDashboard.jsp
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
