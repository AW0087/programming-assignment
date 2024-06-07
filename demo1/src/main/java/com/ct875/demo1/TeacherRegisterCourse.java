/**
 * this is a servlet handling teacherRegisterCourse action posted by teacherDashboard jsp
 */
package com.ct875.demo1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/TeacherRegisterCourse")
public class TeacherRegisterCourse extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        //get username of the user
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //get the courseId
        String courseId = request.getParameter("courseId");

        if(DataHandler.registerCourse(courseId, username)){
            //if successful, redirect to TeacherDashboard servlet
            response.sendRedirect("TeacherDashboard");
        }else{
            //if failed, go to error page
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

//        else if(request.getParameter("deleteCourse") != null){
//            boolean dc = DataHandler.teacherDeregisterCourse(courseId, username);
//            if(dc){
//                response.sendRedirect("TeacherDashboard");
//            }else{
//                request.getRequestDispatcher("error.jsp").forward(request, response);
//            }
//        }
    }
}
