/**
 * this is a CreateCourse servlet handling the createCourse action from adminDashboard.jsp
 */
package com.ct875.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createCourse")
public class CreateCourse extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String courseName = request.getParameter("courseName");
        String semester = request.getParameter("semester");

        // storage the information of course created into database
        boolean courseCreated = DataHandler.createCourse(courseName, semester);

        if (courseCreated) {
            // createCourse successful, redirect to adminDashboard servlet
            response.sendRedirect("AdminDashboard");
        } else {
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
