/**
 * this is a createUser Servlet handling the post method from adminDashboard.jsp to createUser
 */
package com.ct875.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        //get parameter of request which including information of new user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        // storage user information into database by calling dataHandler method
        boolean userCreated = DataHandler.createUser(username, password, firstName,lastName, phone, role);

        if (userCreated) {
            // userCreated successful, sendRedirect to adminDashboard servlet
            response.sendRedirect("AdminDashboard");
        } else {
            // userCreated failed, back to dashboard
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }

    }
}
