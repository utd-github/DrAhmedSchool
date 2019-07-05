/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
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
 * @author pc
 */
public class SubjectsController implements Initializable {

    @FXML
    private TableView<Subjects> subjetsTable;
    @FXML
    private TableColumn<Subjects, String> nocolumn;
    @FXML
    private TableColumn<Subjects, String> sname;
    @FXML
    private TableColumn<Subjects, String> sdes;
    @FXML
    private TableColumn<Subjects, String> actionsColumn;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getSubjects();
    }    

      @FXML
    private void addNew(ActionEvent event) {
             Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newsubject.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
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
            getSubjects();
        });
        stage.show();
    }

    private void getSubjects() {
   
         ObservableList<Subjects> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getSubjects();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Subjects(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("des")
                ));
            }
            initSubjectsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initSubjectsTable(ObservableList<Subjects> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        sname.setCellValueFactory(new PropertyValueFactory<>("name"));
        sdes.setCellValueFactory(new PropertyValueFactory<>("des"));
       actionsColumn.setCellFactory(cellFactory);


        subjetsTable.setItems(Obs);

    }
    
     public void removeSubjects(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeSubjects(id)) {
            getSubjects();
        } else {
            System.out.print("Error Accured while removing");
        }
    }
     
      //Edit exam
    private void editSubjects(Subjects subjects) {
        FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("drahmedschool/views/Newsubject.fxml"));
            root = (Parent)loader.load();

            NewsubjectController con = loader.<NewsubjectController>getController();
            con.setEdit(subjects);
        } catch (IOException ex) {
            Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
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
            getSubjects();
        });
        stage.show();

    }
    
     
    Callback<TableColumn<Subjects, String>, TableCell<Subjects, String>> cellFactory = (final TableColumn<Subjects, String> param) -> {
        final TableCell<Subjects, String> cell = new TableCell<Subjects, String>() {

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
                        Subjects subjects = getTableView().getItems().get(getIndex());
                        removeSubjects(subjects.getId());
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {
                        
                        Subjects subjects = getTableView().getItems().get(getIndex());

                        editSubjects(subjects);
                    });
                    setGraphic(con);
                    setText(null);
                }
            }

           
        };
        return cell;
    };

   

    
    
}
