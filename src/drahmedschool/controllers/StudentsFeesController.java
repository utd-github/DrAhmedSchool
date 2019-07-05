/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Fees;
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
public class StudentsFeesController implements Initializable {
 
    @FXML
    private TableView<Fees> feesTable;
    @FXML
    private TableColumn<Fees, String> nocolumn;
    @FXML
    private TableColumn<Fees, String> actionsColumn;
    @FXML
    private TableColumn<Fees, String> ffrom;
    @FXML
    private TableColumn<Fees, String> amount;
    @FXML
    private TableColumn<Fees, String> type;
    @FXML
    private TableColumn<Fees, String> fdate;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getFees();
    }    

    @FXML
    private void AddNew(ActionEvent event) {
                 Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/NewStudentsFees.fxml"));
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
            getFees();
        });
        stage.show();
    }

    private void getFees() {
   
         ObservableList<Fees> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getFees();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Fees(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("payfrom"),
                        rs.getString("amount"),
                        rs.getString("fdate")
                ));
            }
            initfeesTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(StudentsFeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        //model
    private void initfeesTable(ObservableList<Fees> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ffrom.setCellValueFactory(new PropertyValueFactory<>("payfrom"));
        fdate.setCellValueFactory(new PropertyValueFactory<>("fdate"));   
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        actionsColumn.setCellFactory(cellFactory);

        feesTable.setItems(Obs);

    }
    
 public void removeFees(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeFees(id)) {
            getFees();
        } else {
            System.out.print("Error Accured while removing");
        }
    }
 
   private void editfees(Fees fees) {
           FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("drahmedschool/views/NewStudentsFees.fxml"));
            root = (Parent)loader.load();

            NewStudentsFeesController con = loader.<NewStudentsFeesController>getController();
            con.setEdit(fees);
        } catch (IOException ex) {
            Logger.getLogger(StudentsFeesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

//        Stage properties
        stage.setTitle("Dr Ahmed School");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/drahmedschool/assets/images/logo.png")));
        stage.setOnHidden(e -> {
            getFees();
        });
        stage.show();
 
   }
 
     Callback<TableColumn<Fees, String>, TableCell<Fees, String>> cellFactory = (final TableColumn<Fees, String> param) -> {
      
         final TableCell<Fees, String> cell = new TableCell<Fees, String>() {

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
                         Fees Fees = getTableView().getItems().get(getIndex());
                        removeFees(Fees.getId()); 
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {
                         Fees fees = getTableView().getItems().get(getIndex());

                        editfees(fees);
                    });
                    
                    
                    
                    setGraphic(con);
                    setText(null);
                }
            }

           
        };
        return cell;
    };
    
    
    
}
