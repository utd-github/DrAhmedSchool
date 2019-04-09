/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import com.jfoenix.controls.JFXTextField;
import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Mohamed Jiingad
 */
public class NewteacherController implements Initializable {

    private JFXTextField tname;
    private JFXTextField tphone;
   
    private JFXTextField temail;
    private JFXTextField tjdate;
    private JFXTextField tdes;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitForm(ActionEvent event) {
         dbActions action = new dbActions(dbConnection.dbConnect());
         
         if(checkFields()){
        String name = tname.getText();
        String phone = tphone.getText();
        String email = temail.getText();
        String jdate = tjdate.getText();
        String des = tdes.getText();
        
        
        if(action.AddTeachers(name, phone, email, jdate, des)){ 
            System.out.print("Inserted");
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }else{
            System.out.print("Not inserted");
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errror accured");
            alert.setContentText("Error inserting data");
            alert.show();
        }
        
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errror accured");
            alert.setContentText("Please fill all fields");
            alert.show();
        }
    }

    private boolean checkFields() {
     return !"".equals(tname.getText().trim())&&!"".equals(tphone.getText().trim())
             &&!"".equals(temail.getText().trim())             
             &&!"".equals(tjdate.getText().trim())
             &&!"".equals(tdes.getText().trim());
    }
    
    
    
}
