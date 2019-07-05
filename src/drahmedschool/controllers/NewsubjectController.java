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
import drahmedschool.db.models.Mclass;
import drahmedschool.db.models.Subjects;
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
public class NewsubjectController implements Initializable {

    Subjects subjects;
    @FXML
    private JFXTextField sname;
    @FXML
    private JFXTextField sdes;
    @FXML
    private JFXButton submit;
    @FXML
    private JFXButton Update;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // update hide
        Update.setVisible(false);
    }    

       //setEdit exam
    public void setEdit(Subjects psubjects) {
        
        //submit hide
        submit.setVisible(false);
        Update.setVisible(true);

        subjects = psubjects;
        sname.setText(psubjects.getName());
        sdes.setText(psubjects.getDes());
    }
    
    @FXML
    private void submitForm(ActionEvent event) {
    dbActions action = new dbActions(dbConnection.dbConnect());
    
    if(checkFields()){
        String name = sname.getText();
        String des = sdes.getText();
        
        if(action.AddSubject(name,des)){
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
     return !"".equals(sname.getText().trim())
             &&!"".equals(sdes.getText().trim());
    }

    @FXML
    private void updateForm(ActionEvent event) {
         dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String name = sname.getText();
            String des = sdes.getText();

            if (action.updateSubjcts(subjects.getId(), name, des)) {
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

    void setEdit(Mclass mclass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void cencelForm(ActionEvent event) {
        System.exit(0);
    }

   
    
}
