/**
 * this is a LoginServlet, handling the post method form login.jsp
 */
package com.ct875.demo1;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        //get parameters of username and password from jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //check the login user if valid
        if (DataHandler.isValidUser(username, password)) {
            //store the username in session attribute which can accessible later
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //if valid, get the role and response the user dashboard
            String role = DataHandler.getUserRole(username);
            switch (role) {
                case "teacher":
                    response.sendRedirect("TeacherDashboard");
                    break;
                case "admin":
                    response.sendRedirect("AdminDashboard");
                    break;
                case "student":
                    response.sendRedirect("StudentDashboard");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid role");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            //if not valid, dispatch the error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}