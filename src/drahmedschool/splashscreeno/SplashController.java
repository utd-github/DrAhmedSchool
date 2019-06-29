/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.splashscreeno;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mohamed Jiingad
 */
public class SplashController implements Initializable {

    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     FadeTransition.applyFadeTransition(parent, Duration.seconds(5), (e)->{
            try {
                Parent fxml=FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Login.fxml"));
                parent.getChildren().removeAll();
                parent.getChildren().setAll(fxml);
               
            } catch (IOException ex) {
                Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }    

@FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

}    



    

