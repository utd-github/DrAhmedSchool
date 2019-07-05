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
import drahmedschool.db.models.Payments;
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
public class NewSalaryPaymentsController implements Initializable {

        Payments payments;
    
    @FXML
    private JFXTextField fto;
    @FXML
    private JFXTextField famount;
    @FXML
    private JFXTextField ftype;
    @FXML
    private JFXTextField fdate;
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

      public void setEdit(Payments ppayments) {
           //submit hide
        submit.setVisible(false);
        update.setVisible(true);

        payments = ppayments;
        fto.setText(ppayments.getPayto());
        famount.setText(ppayments.getAmount());
        ftype.setText(ppayments.getType());
        fdate.setText(ppayments.getDate());
    }
    
    @FXML
    private void submitForm(ActionEvent event) {
          dbActions action = new dbActions(dbConnection.dbConnect());
         
          if(checkFields()){
        String type = ftype.getText();
        String payto = fto.getText();
        String amount = famount.getText();
        String kdate = fdate.getText();
        
        
        if(action.AddPayments(type, payto, amount,kdate)){ 
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
     return !"".equals(ftype.getText().trim())
             &&!"".equals(fto.getText().trim())
             &&!"".equals(famount.getText().trim())             
             &&!"".equals(fdate.getText().trim());
    }

    @FXML
    private void cancelForm(ActionEvent event) {
       //
    }

    @FXML
    private void updateForm(ActionEvent event) {
          dbActions action = new dbActions(dbConnection.dbConnect());

        if (checkFields()) {
            String etype = ftype.getText();
            String sfrom = fto.getText();
            String tamount = famount.getText();
            String kdates = fdate.getText();

            if (action.updatePayments(payments.getId(), etype, sfrom, tamount, kdates)) {
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
