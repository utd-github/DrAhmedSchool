/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Musers;
import drahmedschool.db.models.Subjects;
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
public class ManagementsController implements Initializable {
 
    @FXML
    private TableView<Musers> usersTable;
    @FXML
    private TableColumn<Musers, String> nocolumn;
    @FXML
    private TableColumn<Musers, String> uname;
    @FXML
    private TableColumn<Musers, String> uemail;
    @FXML
    private TableColumn<Musers, String> actionsColumn;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getMusers();
        
    }    

    @FXML
    private void addNew(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newmanagements.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
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
          getMusers();
        });
        stage.show();
        
    }

  

    private void getMusers() {
        
         ObservableList<Musers> Obs = FXCollections.observableArrayList();
         
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getMusers();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Musers(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            initusersTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(ManagementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initusersTable(ObservableList<Musers> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        uname.setCellValueFactory(new PropertyValueFactory<>("name"));
        uemail.setCellValueFactory(new PropertyValueFactory<>("email"));
       actionsColumn.setCellFactory(cellFactory);


        usersTable.setItems(Obs);

    }
     public void removeMusers(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeMusers(id)) {
            getMusers();
        } else {
            System.out.print("Error Accured while removing");
        }
    }
     
      private void editmusers(Musers musers) {
            FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("drahmedschool/views/Newmanagements.fxml"));
            root = (Parent)loader.load();

            NewmanagementsController con = loader.<NewmanagementsController>getController();
            con.setEdit(musers);
        } catch (IOException ex) {
            Logger.getLogger(ManagementsController.class.getName()).log(Level.SEVERE, null, ex);
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
            getMusers();
        });
        stage.show();

      }
    Callback<TableColumn<Musers, String>, TableCell<Musers, String>> cellFactory = (final TableColumn<Musers, String> param) -> {
        final TableCell<Musers, String> cell = new TableCell<Musers, String>() {

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
                        Musers musers = getTableView().getItems().get(getIndex());
                        removeMusers(musers.getId());
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {
                        Musers musers = getTableView().getItems().get(getIndex());

                        editmusers(musers);
                    });
                    setGraphic(con);
                    setText(null);
                }
            }

           
        };
        return cell;
    };

   

   
    }
    

