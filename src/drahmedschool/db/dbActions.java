/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Jiingad
 */
public class dbActions {

    private Connection con;

    // Constructor
    public dbActions(Connection con) {
        this.con = con;
    }

    // Login Statement
    public boolean LoginUser(String email, String pass) {

        String sql = "Select id from users where email='" + email + "' and password ='" + pass + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Add Student
    public boolean AddStudent(String name, String phone, String year, String subdate, String rollnum, String email, String dob, String gender) {

        String sql = "INSERT INTO `students`( `name`, `rollno`, `phone`, `email`, `dob`, `gender`, `year`, `sub_date`)"
                + " VALUES ("
                + "'" + name + "',"
                + "'" + rollnum + "',"
                + "'" + phone + "',"
                + "'" + email + "',"
                + "'" + dob + "',"
                + "'" + gender + "',"
                + "'" + year + "',"
                + "'" + subdate + "'"
                + ")";
        Statement st;
        try {
            st = con.createStatement();
            st.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Get Student
    public ResultSet getStudent() {

        String sql = "SELECT * FROM `students`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Add Subject
    public boolean AddSubject(String name, String des) {

        String sql = "INSERT INTO `subjects`( `name`, `des`)"
                + " VALUES ("
                + "'" + name + "',"
                + "'" + des + "'"
                + ")";
        Statement st;
        try {
            st = con.createStatement();
            st.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error Accured: " + ex.getMessage());
        }

        return false;
    }

    //Get Subjects
    public ResultSet getSubjects() {
        String sql = "SELECT * FROM `subjects`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Add Teachers
    public boolean AddTeachers(String name, String phone, String email, String jdate, String des) {

        String sql = "INSERT INTO `teacher`( `name`, `email`, `phone`, `jdate`, `des`)"
                + " VALUES ("
                + "'" + name + "',"
                + "'" + phone + "',"
                + "'" + email + "',"
                + "'" + jdate + "',"
                + "'" + des + "'"
                + ")";
        Statement st;
        try {
            st = con.createStatement();
            st.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // Get Teachers
    public ResultSet getTeachers() {

        String sql = "SELECT * FROM `teacher`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    // Add Events
    public boolean AddEvents(String eTitle, String eLocation, String eDate, String eDes) {

        String sql = "INSERT INTO `events`(  `title`, `location`, `edate`, `des`)"
                + " VALUES ("
                + "'" + eTitle + "',"
                + "'" + eLocation + "',"
                + "'" + eDate + "',"
                + "'" + eDes + "'"
                + ")";
        Statement st;
        try {
            st = con.createStatement();
            st.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error Accured: " + ex.getMessage());
        }

        return false;
    }

    //Get Events
    public ResultSet getEvents() {
        String sql = "SELECT * FROM `events`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Add Assignments
    public boolean AddAssignments(String aTitle, String acid, String adeadline, String aissue, String aDes) {

        String sql = "INSERT INTO `assignments`( `class_id`, `title`, `issued`, `deadline`, `description`)"
                + " VALUES ("
                + "'" + acid + "',"
                + "'" + aTitle + "',"
                + "'" + adeadline + "',"
                + "'" + aissue + "',"
                + "'" + aDes + "'"
                + ")";
        Statement st;
        try {
            st = con.createStatement();
            st.execute(sql);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error Accured: " + ex.getMessage());
        }

        return false;
    }

    //Get Assignments
    public ResultSet getAssignments() {
        String sql = "SELECT * FROM `assignments`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Close connection
    public void CloseConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.print("Database Connection Closed");
            } catch (SQLException ex) {
                Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean removeAssignments(String id) {
        String sql = "DELETE FROM `assignments` WHERE id='" + id + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean removeTeachers(String id) {
        String sql = "DELETE FROM `teacher` WHERE id='" + id + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean removeSubjects(String id) {
        String sql = "DELETE FROM `subjects` WHERE id='" + id + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public boolean removeEvents(String id) {
        String sql = "DELETE FROM `events` WHERE id='" + id + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
       public boolean removeStudents(String id) {
        String sql = "DELETE FROM `students` WHERE id='" + id + "';";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
}
