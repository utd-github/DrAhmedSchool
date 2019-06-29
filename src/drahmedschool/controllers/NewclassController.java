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
public class NewclassController implements Initializable {

    @FXML
    private JFXTextField cname;
    @FXML
    private JFXTextField croom;
     @FXML
    private JFXTextField tid;
      @FXML
    private JFXTextField subid;
       @FXML
    private JFXTextField des;
   

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
        String name = cname.getText();
        String room = croom.getText();
        String teid = tid.getText();
        String subjid = subid.getText();
        String tdes = des.getText();
        
        if(action.AddMclass(name, room, teid, subjid, tdes)){ 
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
     return !"".equals(cname.getText().trim())
             &&!"".equals(croom.getText().trim())
             &&!"".equals(tid.getText().trim())             
             &&!"".equals(subid.getText().trim())
             &&!"".equals(des.getText().trim());
    }
    
    
    
}


