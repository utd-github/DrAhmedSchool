/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Musers;
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
public class NewmanagementsController implements Initializable {

     Musers musers;
    
    @FXML
    private JFXTextField uname;
    @FXML
    private JFXTextField uemail;
    @FXML
    private JFXPasswordField upass;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton sumbit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // update hide
        update.setVisible(false);
    }    

      public void setEdit(Musers pmusers) {
     //submit hide
        sumbit.setVisible(false);
        update.setVisible(true);
        
        musers = pmusers;
        uname.setText(pmusers.getName());
        uemail.setText(pmusers.getEmail());
        upass.setText(pmusers.getPassword());
    }
    
    @FXML
    private void submitForm(ActionEvent event) {
        dbActions action = new dbActions(dbConnection.dbConnect());
    
    if(checkFields()){
        String name = uname.getText();   
        String email = uemail.getText();

        String password = upass.getText();
        
        if(action.AddMussers(name,email,password)){
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
     return !"".equals(uname.getText().trim())
             &&!"".equals(uemail.getText().trim())
             &&!"".equals(upass.getText().trim());
    }

    @FXML
    private void cancelForm(ActionEvent event) {
       //
    }

    @FXML
    private void updateForm(ActionEvent event) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String name = uname.getText();
            String email = uemail.getText();
            String pass = upass.getText();

            if (action.updateMusers(musers.getId(), name, email, pass)) {
                System.out.print("updated");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.print("Not updated");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errror accured");
                alert.setContentText("Error updating data");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errror accured");
            alert.setContentText("Please fill all fields");
            alert.show();
        }
    
    
    }
    
    
        
    

  
    
    
}
