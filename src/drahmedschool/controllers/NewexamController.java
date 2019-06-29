/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import com.jfoenix.controls.JFXButton;
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
public class NewexamController implements Initializable {

    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField sid;
    @FXML
    private JFXTextField edate;
    @FXML
    private JFXTextField eclass;
    @FXML
    private JFXTextField memo;
   

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
        String etype = type.getText();
        String eecla = eclass.getText();
        String stdid = sid.getText();
        String kdates = edate.getText();
        String ememos = memo.getText();
        
        if(action.AddExams(etype,eecla,stdid,kdates,ememos)){ 
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
     return !"".equals(type.getText().trim())
             &&!"".equals(eclass.getText().trim())
             &&!"".equals(sid.getText().trim())             
             &&!"".equals(edate.getText().trim())
             &&!"".equals(memo.getText().trim());
    }
    
    
}
