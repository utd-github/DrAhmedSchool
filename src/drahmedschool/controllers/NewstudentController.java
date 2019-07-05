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
import drahmedschool.db.models.Students;
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
public class NewstudentController implements Initializable {

        Students students;
    @FXML
    private JFXTextField stdname;
    @FXML
    private JFXTextField stdphone;
    @FXML
    private JFXTextField stdcyear;
    @FXML
    private JFXTextField stdsubdate;
    @FXML
    private JFXTextField stdrnum;
    @FXML
    private JFXTextField stdemail;
    @FXML
    private JFXTextField stddob;
    @FXML
    private JFXTextField stdgender;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          update.setVisible(false);
    }    

   public  void setEdit(Students pstudent) {
        //submit hide
        submit.setVisible(false);
        update.setVisible(true);

        students = pstudent;
        stdname.setText(pstudent.getName());
        stdphone.setText(pstudent.getPhone());
        stdcyear.setText(pstudent.getEmail());
        stdsubdate.setText(pstudent.getSubdate());  
        stdemail.setText(pstudent.getEmail()); 
        stddob.setText(pstudent.getDob());
        stdgender.setText(pstudent.getGender());
        stdrnum.setText(pstudent.getNo());

    }
   
    @FXML
    private void submitForm(ActionEvent event) {
        
        dbActions action = new dbActions(dbConnection.dbConnect());
        
        if(checkFields()){
        String name = stdname.getText();
        String phone = stdphone.getText();
        String year = stdcyear.getText();
        String subdate = stdsubdate.getText();
        String rollnum = stdrnum.getText();
        String email = stdemail.getText();
        String dob = stddob.getText();
        String gender = stdgender.getText();
        
        if(action.AddStudent(name,phone,year,subdate,rollnum,email,dob,gender)){
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
     return !"".equals(stdname.getText().trim())&&!"".equals(stdphone.getText().trim())
             &&!"".equals(stdcyear.getText().trim())             
             &&!"".equals(stdsubdate.getText().trim())&&!"".equals(stdrnum.getText().trim())
             &&!"".equals(stdemail.getText().trim())&&!"".equals(stddob.getText().trim())
             &&!"".equals(stdgender.getText().trim());
    }

    @FXML
    private void cancelForm(ActionEvent event) {
        //
    }

    @FXML
    private void updateForm(ActionEvent event) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String name = stdname.getText();
            String phone = stdphone.getText();
            String email = stdemail.getText();
            String dob = stddob.getText();
            String year = stdcyear.getText(); 
            String subdate = stdsubdate.getText(); 
            String rnum = stdrnum.getText(); 
            String gender = stdgender.getText();




            if (action.updateStudents(students.getId(), name, phone, email, dob, year, subdate,rnum,gender)) {
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
