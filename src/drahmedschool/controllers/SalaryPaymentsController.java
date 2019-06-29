/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Payments;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mohamed Jiingad
 */
public class SalaryPaymentsController implements Initializable {

    @FXML
    private TableView<Payments> paymentsTable;
    @FXML
    private TableColumn<Payments, String> nocolumn;
    @FXML
    private TableColumn<Payments, String> actionsColumn;
    @FXML
    private TableColumn<Payments, String> payto;
    @FXML
    private TableColumn<Payments, String> amount;
    @FXML
    private TableColumn<Payments, String> type;
    @FXML
    private TableColumn<Payments, String> pdate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getPayments();
    }    

    @FXML
    private void AddNew(ActionEvent event) {
 
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/NewSalaryPayment.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage= new Stage();
        stage.setScene(scene);
        
//        Stage properties
        stage.setTitle("Dr Ahmed School");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/drahmedschool/assets/images/logo.png")));
          stage.setOnHidden(e -> {
           getPayments();
        });
        stage.show();
    }
    
 private void getPayments() {
   
         ObservableList<Payments> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getPayments();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Payments(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("payto"),
                        rs.getString("amount"),
                        rs.getString("fdate")
                ));
            }
            initpaymentsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(SalaryPaymentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            //Model
    private void initpaymentsTable(ObservableList<Payments> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        payto.setCellValueFactory(new PropertyValueFactory<>("payto"));
        pdate.setCellValueFactory(new PropertyValueFactory<>("date"));   
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        actionsColumn.setCellFactory(cellFactory);

        paymentsTable.setItems(Obs);

    }
    
 public void removePayments(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removePayments(id)) {
            getPayments();
        } else {
            System.out.print("Error Accured while removing");
        }
    }
     Callback<TableColumn<Payments, String>, TableCell<Payments, String>> cellFactory = (final TableColumn<Payments, String> param) -> {
      
         final TableCell<Payments, String> cell = new TableCell<Payments, String>() {

            final Button rbtn = new Button("Remove");
            final Button ebtn = new Button("Edit");

            final HBox con = new HBox();
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    con.getChildren().clear();
                    con.setSpacing(5.0);
                    rbtn.getStyleClass().add("flatbutton");
                    ebtn.getStyleClass().add("flatbutton");
                    con.getChildren().addAll(rbtn, ebtn);

                    rbtn.setOnAction(event -> {
                         Payments Payments = getTableView().getItems().get(getIndex());
                        removePayments(Payments.getId()); 
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {
                        System.out.println("Editintg");
                    });
                    setGraphic(con);
                    setText(null);
                }
            }
        };
        return cell;
    };
    
}
