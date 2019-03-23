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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void singinAction(ActionEvent event) {
        loadMainView();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private void loadMainView() {
        Stage stage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/MainView.fxml"));
            Scene scene = new Scene(root);

            //  Stage properties
            stage.setTitle("Dr Ahmed School");
            stage.setMaximized(true);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/drahmedschool/assets/images/logo.png")));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.show();

    }

}
