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
public class NeweventController implements Initializable {

    @FXML
    private JFXTextField eTitle;
    @FXML
    private JFXTextField eLocation;
    @FXML
    private JFXTextField eDate;
    @FXML
    private JFXTextField EDes;

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
        String name = eTitle.getText();
        String phone = eLocation.getText();
        String year = eDate.getText();
        String subdate = EDes.getText();
        
        
        if(action.AddEvents(phone, subdate, subdate, year)){
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
     return !"".equals(eTitle.getText().trim())&&!"".equals(eLocation.getText().trim())
             &&!"".equals(eDate.getText().trim())
             &&!"".equals(EDes.getText().trim());
    }
    
    
    
}
