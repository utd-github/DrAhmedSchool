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
import drahmedschool.db.models.Exams;
import drahmedschool.db.models.Musers;
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
public class NewexamController implements Initializable {

    //call exam
    Exams exam;
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
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton submit;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // update hide
        update.setVisible(false);
    }

    //setEdit exam
    public void setEdit(Exams pexam) {
        
        //submit hide
        submit.setVisible(false);
        update.setVisible(true);

        exam = pexam;
        type.setText(pexam.getType());
        eclass.setText(pexam.getEclass());
        sid.setText(pexam.getSid());
        edate.setText(pexam.getEdate());
        memo.setText(pexam.getMemo());

    }

    @FXML
    private void submitForm(ActionEvent event) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String etype = type.getText();
            String eecla = eclass.getText();
            String stdid = sid.getText();
            String kdates = edate.getText();
            String ememos = memo.getText();

            if (action.AddExams(etype, eecla, stdid, kdates, ememos)) {
                System.out.print("Inserted");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.print("Not inserted");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errror accured");
                alert.setContentText("Error inserting data");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errror accured");
            alert.setContentText("Please fill all fields");
            alert.show();
        }

    }

    private boolean checkFields() {
        return !"".equals(type.getText().trim())
                && !"".equals(eclass.getText().trim())
                && !"".equals(sid.getText().trim())
                && !"".equals(edate.getText().trim())
                && !"".equals(memo.getText().trim());
    }

    //update
    @FXML
    private void updateExam(ActionEvent event) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String etype = type.getText();
            String eecla = eclass.getText();
            String stdid = sid.getText();
            String kdates = edate.getText();
            String ememos = memo.getText();

            if (action.updateExams(exam.getId(), etype, eecla, stdid, kdates, ememos)) {
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
