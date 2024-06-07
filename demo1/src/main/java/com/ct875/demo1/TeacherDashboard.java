/**
 * this is a TeacherDashboard servlet, dispatched by LoginServlet
 */
package com.ct875.demo1;
import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/TeacherDashboard")
public class TeacherDashboard extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<String[]> courseTeacherList = DataHandler.getCourseTeacher();
//        request.setAttribute("courseTeacherList", courseTeacherList);
//        request.getRequestDispatcher("teacherDashboard.jsp").forward(request, response);

        // retrieve the username attribute
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //get the teacher registered courses information and save that in request
        List<String[]> teacherRegisteredCourses = DataHandler.getTeacherRegisteredCourse(username);
        request.setAttribute("teacherRegisteredCourses", teacherRegisteredCourses);

        //dispatch to teacherDashboard.jsp to show the coursed registered.
        request.getRequestDispatcher("teacherDashboard.jsp").forward(request, response);
    }
}
