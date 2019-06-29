package drahmedschool.splashscreeno;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import drahmedschool.controllers.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mohamed Jiingad
 */
public class Launch extends Application{

    public static Stage stage = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        Parent root= FXMLLoader.load(getClass().getResource("Splash.fxml"));
        Scene scene= new Scene(root);
        
       
        
        stage.setScene(scene);
        
        
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
        
       this.stage = stage;
        
      
    } 
   
        
}
