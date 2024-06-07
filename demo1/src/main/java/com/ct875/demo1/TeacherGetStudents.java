/**
 * this is a servlet handling the TeacherGetStudent action requested by teacherDashboard jsp
 */
package com.ct875.demo1;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/TeacherGetStudents")
public class TeacherGetStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get courseId
        String courseId = request.getParameter("courseId");

        //save the info retrieved from database into a list
        List<Object[]> studentsOfCourse = DataHandler.getStudentsOfCourse(courseId);

        //save the list retrieved into request attribute facilitating to be shown in jsp
        request.setAttribute("studentsOfCourse", studentsOfCourse);
        request.setAttribute("courseId", courseId);

        //dispatch to studentsOfTheCourse jsp
        request.getRequestDispatcher("studentsOfTheCourse.jsp").forward(request, response);
    }
}
