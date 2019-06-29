/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.db;

import com.jfoenix.controls.JFXTextField;
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
    
       // Add Payments
    public boolean AddPayments(String type, String fto, String famount, String fdate) {

        String sql = "INSERT INTO `payments`( `type`, `Amount`, `payto`, `fdate`)"
                + " VALUES ("
                + "'" + type + "',"
                + "'" + fto + "',"
                + "'" + famount + "',"
                + "'" + fdate + "' "
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
    
     // Get Payments
    public ResultSet getPayments() {

        String sql = "SELECT * FROM `payments`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
     
      // Add fees
    public boolean AddFees(String type, String ffrom, String famount, String fdate) {

        String sql = "INSERT INTO `fees`( `type`, `Amount`, `payfrom`, `fdate`)"
                + " VALUES ("
                + "'" + type + "',"
                + "'" + ffrom + "',"
                + "'" + famount + "',"
                + "'" + fdate + "' "
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
    
     // Get fees
    public ResultSet getFees() {

        String sql = "SELECT * FROM `fees`";
        Statement st;
        try {
            st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
     
//Add Classes
    public boolean AddMclass(String name, String tid, String subid, String room, String des){
        String sql = "INSERT INTO `mclass`(`name`, `tid`, `subid`, `room`, `des`)"
                + " VALUES ("
                + "'" + name + "',"
                + "'" + tid + "',"
                + "'" + subid + "',"
                + "'" + room + "',"
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
    //Get Classes
    public ResultSet getMclass() {

        String sql = "SELECT * FROM `mclass`";
        Statement st;
        try {
            st = con.createStatement();          
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(dbActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
   //Add Exams
    public boolean AddExams(String type, String eclass, String sid, String edate, String memo){
        String sql = "INSERT INTO `exam`( `type`, `eclass`, `sid`, `edate`, `memo`)"
                + " VALUES ("
                + "'" + type + "',"
                + "'" + eclass + "',"
                + "'" + sid + "',"
                + "'" + edate + "',"
                + "'" + memo + "'"
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
    //Get Exams
    public ResultSet getExams() {
        String sql = "SELECT * FROM `exam`";
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
    //remove Teachers
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
        //remove Subjects
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
       //remove Students
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
//remove class
    public boolean removeMclass(String id) {
       
 String sql = "DELETE FROM `mclass` WHERE id='" + id + "';";
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

//remove Exam
    public boolean removeExams(String id) {
       
 String sql = "DELETE FROM `exam` WHERE id='" + id + "';";
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

    public boolean removePayments(String id) {
   String sql = "DELETE FROM `payments` WHERE id='" + id + "';";
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

    public boolean removeFees(String id) {
    String sql = "DELETE FROM `fees` WHERE id='" + id + "';";
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
