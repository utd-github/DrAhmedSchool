/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Jiingad
 */
public class FinanceController implements Initializable {

    @FXML
    private BorderPane financecntainer;
    @FXML
    private JFXButton stdfees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadContainerUI("StudentsFees");
    }

    private void loadContainerUI(String ui) {
        Stage stage = new Stage();
        String uiPath = "drahmedschool/views/" + ui + ".fxml";
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(uiPath));
            financecntainer.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StudentFeesAction(ActionEvent event) {
        loadContainerUI("StudentsFees");
    }

    @FXML
    private void SalaryPaymentsAction(ActionEvent event) {
        loadContainerUI("SalaryPayments");
    }

}
