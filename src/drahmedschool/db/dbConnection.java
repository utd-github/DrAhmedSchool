/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class dbConnection {

    private static Connection con = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String DbName = "school";
    private static final String user = "root";
    private static final String pass = "";
    private static final String ConUrl = "jdbc:mysql://localhost:3306/" + DbName;

    public static Connection dbConnect() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(ConUrl, user, pass);
            System.out.print("Database Connected.. \n");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            System.out.print("Database Connection failed: \t " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Database Connection Driver not found: \t " + ex.getMessage());
        }
        return null;
    }

}
