/**
 * this is a DataHandler class handling all method connected to database
 */
package com.ct875.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    //url, username, password of database
    private static final String URL = "jdbc:mysql://localhost:3306/student_assessment_sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    //this method checks the login user is valid or not
    public static boolean isValidUser(String username, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //sql query statement
            String query = "SELECT Role FROM User WHERE Username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //set parameters taking place of placeholder mark in sql statement
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    //this method query user role by having the parameter of the username
    public static String getUserRole(String username) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "SELECT Role FROM User WHERE Username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        //return the role
                        return resultSet.getString("Role");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // this method insert user information into database creating a new user
    public static boolean createUser(String username, String password, String firstName, String lastName, String phone, String role) {
        boolean success = false;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "INSERT INTO User (Username, Password, FirstName, LastName, Phone, Role) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, firstName);
                statement.setString(4, lastName);
                statement.setString(5, phone);
                statement.setString(6, role);

                //if a new row inserted into database successful, return true
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    //this method inserts a course record into database
    public static boolean createCourse(String courseName, String semester) {
        boolean success = false;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "INSERT INTO Course (CourseName, Semester) VALUES ( ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, courseName);
                statement.setString(2, semester);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    //this method gets all users information used in adminDashboard servlet
    public static List<String[]> getAllUsers() {
        List<String[]> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "SELECT UserId,Username,FirstName,LastName,Phone,Role FROM user";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] user = new String[6]; // Assuming there are 6 columns in the user table
                        user[0] = String.valueOf(resultSet.getInt("UserID"));
                        user[1] = resultSet.getString("Username");
                        user[2] = resultSet.getString("FirstName");
                        user[3] = resultSet.getString("LastName");
                        user[4] = resultSet.getString("Phone");
                        user[5] = resultSet.getString("Role");

                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    //this method retrieves all courses information from database, called by AdminDashboard servlet
    public static List<String[]> getAllCourses() {
        List<String[]> courseList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "SELECT * FROM course";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] course = new String[3]; // Assuming there are 3 columns in the course table
                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
                        course[1] = resultSet.getString("CourseName");
                        course[2] = resultSet.getString("Semester");

                        courseList.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }
//
//    public static List<String[]> getCourseTeacher() {
//        List<String[]> courseTeacherList = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            String query = "select   c.*, tc.Username  as 'Teacher' FROM Course c\n" +
//                    "    left join (select u.userID,u.Username, uc.courseId from usercourse uc join user u on uc.UserID = u.UserID where u.Role = 'teacher') tc\n" +
//                    "        on c.CourseId = tc.CourseID;";
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        String[] course = new String[4]; // Assuming there are 3 columns in the course table
//                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
//                        course[1] = resultSet.getString("CourseName");
//                        course[2] = resultSet.getString("Semester");
//                        course[3] = resultSet.getString("Teacher");
//
//                        courseTeacherList.add(course);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return courseTeacherList;
//    }

    //this method retrieves all courses registered by a certain teacher, called by the teacherAdmin servlet
    public static List<String[]> getTeacherRegisteredCourse(String username){
        List<String[]> teacherRegisteredCourses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "select c.* from course c join usercourse uc on c.courseId = uc.courseId " +
                    "join user u on u.userId = uc.userId where u.username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] course = new String[3]; // Assuming there are 3 columns in the course table
                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
                        course[1] = resultSet.getString("CourseName");
                        course[2] = resultSet.getString("Semester");

                        teacherRegisteredCourses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherRegisteredCourses;
    }

    //this method registers a course for a teacher, called by the teacherDashboard servlet
    public static boolean registerCourse(String courseId, String userName) {
        Boolean success = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "INSERT INTO usercourse (CourseID, UserID) " +
                    "SELECT ?, userId FROM user WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, courseId);
                statement.setString(2, userName);
                statement.executeUpdate();

                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    //this method deregisters a course for a certain teacher, called by TeacherRegisterCourse servlet
    public static boolean teacherDeregisterCourse(String courseId, String userName) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql delete statement for usercourse
            String usercourseQuery = "DELETE FROM usercourse WHERE CourseID = ? AND UserID = (SELECT UserID FROM user WHERE Username = ?)";
            //sql delete statement for assessment
            String assessmentQuery = "DELETE FROM assessment WHERE CourseID = ? AND UserID = (SELECT UserID FROM user WHERE Username = ?)";
            try (PreparedStatement statement = connection.prepareStatement(usercourseQuery)) {
                statement.setString(1, courseId);
                statement.setString(2, userName);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
            // execute the assessment delete query
            try (PreparedStatement statement = connection.prepareStatement(assessmentQuery)) {
                statement.setString(1, courseId);
                statement.setString(2, userName);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    //this method retrieves all students who enroll in a certain course
    //this method is called by TeacherGetStudents servlet
    public static List<Object[]> getStudentsOfCourse(String courseId){
        List<Object[]> studentsOfCourse = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "select distinct u.UserId, u.Username, u.phone, a.quiz, a.Assignment, a.FinalExam from user u\n" +
                    "left join usercourse uc on u.userId = uc.userId\n" +
                    "left join assessment a on u.userId = a.UserID where uc.courseId = ? and u.role = 'student'";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] student = new Object[6];
                        student[0] = resultSet.getString("UserId");
                        student[1] = resultSet.getString("Username");
                        student[2] = resultSet.getString("phone");
                        student[3] = resultSet.getString("quiz");
                        student[4] = resultSet.getString("assignment");
                        student[5] = resultSet.getString("finalExam");
                        studentsOfCourse.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentsOfCourse;
    }

    //this method storages marks of a student graded by teacher into database, which is called by TeacherGradeStudent servlet
    public static boolean saveStudentGrade(String studentId, String courseId, double quizGrade, double assignmentGrade, double finalExamGrade) {
        boolean success = false;
        //sql statements
        String selectQuery = "SELECT COUNT(*) FROM assessment WHERE userid = ? AND courseid = ?";
        String insertQuery = "INSERT INTO assessment (userid, courseid, quiz, assignment, finalexam) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            // set query parameter
            selectStatement.setString(1, studentId);
            selectStatement.setString(2, courseId);

            // execute select statement
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) == 0) { // 如果不存在评估记录
                    // set parameters for insert statement
                    insertStatement.setString(1, studentId);
                    insertStatement.setString(2, courseId);
                    insertStatement.setDouble(3, quizGrade);
                    insertStatement.setDouble(4, assignmentGrade);
                    insertStatement.setDouble(5, finalExamGrade);

                    // execute insert
                    int rowsInserted = insertStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        success = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }


    //retrieve available courses for teacher, this method is called by TeacherDashboard servlet
    public static List<String[]> getTeacherAvailableCourse() {
        List<String[]> courseList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "SELECT DISTINCT c.* FROM course c WHERE NOT EXISTS (\n" +
                    "    SELECT 1 FROM usercourse uc\n" +
                    "    WHERE uc.courseId = c.courseId\n" +
                    "      AND uc.userId in (SELECT userId FROM user WHERE role = 'teacher')\n" +
                    ");";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] course = new String[3]; // Assuming there are 3 columns in the course table
                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
                        course[1] = resultSet.getString("CourseName");
                        course[2] = resultSet.getString("Semester");

                        courseList.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }

    //retrieve courses enrolled by a certain student, this method is called by StudentDashboard servlet
    public static List<String[]> getStudentEnrolledCourses(String username) {
        List<String[]> studentEnrolledCourses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "select c.* from course c join usercourse uc on c.courseId = uc.courseId " +
                    "join user u on u.userId = uc.userId where u.username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                //set parameter of '?' in sql
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] course = new String[3]; // Assuming there are 3 columns in the course table
                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
                        course[1] = resultSet.getString("CourseName");
                        course[2] = resultSet.getString("Semester");

                        studentEnrolledCourses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentEnrolledCourses;
    }

    // retrieve courses which are still available to the student(not enrolled by the student)
    public static List<String[]> getStudentAvailableCourse(String username) {
        List<String[]> courseList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql
            String query = "SELECT DISTINCT c.* FROM course c\n" +
                    "WHERE NOT EXISTS (\n" +
                    "SELECT 1 FROM usercourse uc WHERE uc.courseId = c.courseId\n" +
                    "AND uc.userId = (SELECT userId FROM user WHERE username = ?)\n" +
                    ");";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String[] course = new String[3]; // Assuming there are 3 columns in the course table
                        course[0] = String.valueOf(resultSet.getInt("CourseID"));
                        course[1] = resultSet.getString("CourseName");
                        course[2] = resultSet.getString("Semester");

                        courseList.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }

    //add a record of student enrolling a course, this method is called by StudentEnrollCourse servlet
    public static boolean studentEnrollCourse(String courseId, String username) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //check if the user has already enrolled the course
            if (!isEnrolled(courseId, username, connection)) {
                // if the user has not enrolled the course, then insert the record
                // sql insert statement
                String query = "INSERT INTO usercourse (CourseID, UserID) VALUES (?, (SELECT UserID FROM user WHERE Username = ?))";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, courseId);
                    statement.setString(2, username);
                    int rowsAffected = statement.executeUpdate();
                    // insert successful
                    if (rowsAffected > 0) {
                        success = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // this method checks if the user has already enrolled the course
    private static boolean isEnrolled(String courseId, String username, Connection connection) throws SQLException {
        //sql query statement
        String query = "SELECT * FROM usercourse WHERE CourseID = ? AND UserID = (SELECT UserID FROM user WHERE Username = ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, courseId);
            statement.setString(2, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                // if query result has record, return true
                return resultSet.next();
            }
        }
    }

    //delete the record of a student enrolling a course
    //this method is called by StudentUnenrollCourse servlet
    public static boolean studentUnenrollCourse(String courseId, String username) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql delete statement for usercourse
            String usercourseQuery = "DELETE FROM usercourse WHERE CourseID = ? AND UserID = (SELECT UserID FROM user WHERE Username = ?)";
            //sql delete statement for assessment
            String assessmentQuery = "DELETE FROM assessment WHERE CourseID = ? AND UserID = (SELECT UserID FROM user WHERE Username = ?)";
            try (PreparedStatement statement = connection.prepareStatement(usercourseQuery)) {
                statement.setString(1, courseId);
                statement.setString(2, username);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
            // execute the assessment delete query
            try (PreparedStatement statement = connection.prepareStatement(assessmentQuery)) {
                statement.setString(1, courseId);
                statement.setString(2, username);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    //student retrieve the grades information of a course, this method is called by StudentViewGrades servlet
    public static Object[] getStudentGrades(String userName, String courseId) {
        Object[] assessments = new Object[3];

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            //sql statement
            String query = "SELECT quiz, assignment, finalexam FROM assessment a join user u on a.userId = u.userId" +
                    " WHERE courseId = ? and userName = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, courseId);
                statement.setString(2, userName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) { // 移动结果集指针到第一行
                        assessments[0] = resultSet.getDouble("Quiz");
                        assessments[1] = resultSet.getDouble("Assignment");
                        assessments[2] = resultSet.getDouble("FinalExam");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assessments;
    }


}
