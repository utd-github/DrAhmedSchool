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
import drahmedschool.db.models.Teachers;
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

    Teachers teachers;
    @FXML
    private JFXTextField tname;
    @FXML
    private JFXTextField tphone;
   
    @FXML
    private JFXTextField temail;
    @FXML
    private JFXTextField tjdate;
    @FXML
    private JFXTextField tdes;
    @FXML
    private JFXButton submit;
    @FXML
    private JFXButton upadte;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       upadte.setVisible(false);
    }    

      //setEdit exam
    public void setEdit(Teachers pteachers) {
        
        //submit hide
        submit.setVisible(false);
        upadte.setVisible(true);

        teachers = pteachers;
        tname.setText(pteachers.getName());
        tphone.setText(pteachers.getPhone());
        temail.setText(pteachers.getEmail());
        tjdate.setText(pteachers.getJdate());
        tdes.setText(pteachers.getDes());

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
     return !"".equals(tname.getText().trim())
             &&!"".equals(tphone.getText().trim())
             &&!"".equals(temail.getText().trim())             
             &&!"".equals(tjdate.getText().trim())
             &&!"".equals(tdes.getText().trim());
    }

    @FXML
    private void updateForm(ActionEvent event) {
      dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String name = tname.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String tdate = tjdate.getText();
            String des = tdes.getText();

            if (action.updateTeachers(teachers.getId(), name, phone, email, tdate, des)) {
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

    @FXML
    private void cancelForm(ActionEvent event) {
        //
    }

    
    
    
    }
