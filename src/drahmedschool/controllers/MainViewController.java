/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class MainViewController implements Initializable {

    @FXML
    private BorderPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadContainerUI("Dashboard");
    }

    @FXML
    private void showDashboard(ActionEvent event) {
        loadContainerUI("Dashboard");
    }

    @FXML
    private void showAssignments(ActionEvent event) {
        loadContainerUI("Assignments");
    }

    @FXML
    private void showStudents(ActionEvent event) {
        loadContainerUI("Students");
    }

       @FXML
    private void showTeachers(ActionEvent event) {
        loadContainerUI("Teachers");
    }
    
      @FXML
    private void showClasses(ActionEvent event) {
                loadContainerUI("Classes");

    }
    
     @FXML
    private void showSubjects(ActionEvent event) {
                loadContainerUI("Subjects");

    }
    
     @FXML
    private void showExams(ActionEvent event) {
        loadContainerUI("Exams");
    }
    private void loadContainerUI(String ui) {
        Stage stage = new Stage();
        String uiPath = "drahmedschool/views/" + ui + ".fxml";
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(uiPath));
            container.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   

  

 

}
