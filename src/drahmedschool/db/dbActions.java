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
}
