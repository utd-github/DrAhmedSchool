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
public class NewassignmentsController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField cid;
    @FXML
    private JFXTextField des;
    @FXML
    private JFXTextField aissue;
    @FXML
    private JFXTextField deadline;

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
        String atitle = title.getText();
        String aclsid = cid.getText();
        String issue = aissue.getText();
        String adeadline = deadline.getText();
        String ades = des.getText();
        
        
        if(action.AddAssignments(atitle, aclsid, adeadline, issue, ades)){ 
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
     return !"".equals(title.getText().trim())&&!"".equals(cid.getText().trim())                        
             &&!"".equals(aissue.getText().trim())
             &&!"".equals(deadline.getText().trim())  
             &&!"".equals(des.getText().trim());
    }
    
    
}
