/**
 * this is a servlet handling get method from teacherDashboard.jsp
 */
package com.ct875.demo1;

import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/GetTeacherAvailableCourse")
public class GetTeacherAvailableCourse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // call getStudentAvailableCourse method of DataHandler to retrieve teacher available courses
        List<String[]> teacherAvailableCourses = DataHandler.getTeacherAvailableCourse();

        // save retrieved list into request attribute facilitating to be used in jsp
        request.setAttribute("teacherAvailableCourses", teacherAvailableCourses);

        //dispatch to teacherAvailableCourse.jsp
        request.getRequestDispatcher("teacherAvailableCourse.jsp").forward(request, response);
    }
}
