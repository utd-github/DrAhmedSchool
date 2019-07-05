/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pc
 */
public class DrAhmedSchool extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/SplashScren.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
//        Stage properties
        stage.setTitle("Dr Ahmed School");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/drahmedschool/assets/images/logo.png")));
        stage.show();
    }
    
}
