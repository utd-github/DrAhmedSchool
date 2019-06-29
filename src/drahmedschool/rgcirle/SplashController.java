/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.rgcirle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mohamed Jiingad
 */
public class SplashController implements Initializable {

    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("drahmedschool/views/Login.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setRotate(c1, true, 360, 10);    
        setRotate(c2, true, 180, 18);
        setRotate(c3, true, 145, 24);

        
    }    

    private void setRotate(Circle c, boolean reverse, int angle, int duration) {
    
        RotateTransition rotateTransition = new RotateTransition (Duration.seconds(duration),c); 
        
        rotateTransition.setAutoReverse(reverse);
        
        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(3);
        rotateTransition.setCycleCount(18);
        rotateTransition.play();
               

    }
    
}
