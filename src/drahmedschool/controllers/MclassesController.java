/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Mclass;

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
public class MclassesController implements Initializable {

    @FXML
    private TableView<Mclass> classTable;
    @FXML
    private TableColumn<Mclass, String> nocolumn;
    @FXML
    private TableColumn<Mclass, String> cname;
    @FXML
    private TableColumn<Mclass, String> tid;
    @FXML
    private TableColumn<Mclass, String> subid;
    @FXML
    private TableColumn<Mclass, String> croom;
    @FXML
    private TableColumn<Mclass, String> actionsColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         getMclass();
    }    


    @FXML
    private void addNew(ActionEvent event) {
             Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newclass.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MclassesController.class.getName()).log(Level.SEVERE, null, ex);
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
            getMclass();
        
         });
                stage.show();
    }

    private void getMclass() {
    
        ObservableList<Mclass> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getMclass();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Mclass(
                        //Database
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("room"),
                        rs.getString("tid"),
                        rs.getString("subid"),
                        rs.getString("des")
                ));
            }
            initclassTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initclassTable(ObservableList<Mclass> Obs) {

        //Model
         nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        croom.setCellValueFactory(new PropertyValueFactory<>("room"));
        tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
        subid.setCellValueFactory(new PropertyValueFactory<>("subid"));
         actionsColumn.setCellFactory(cellFactory);

        classTable.setItems(Obs);
 
    }
    
 public void removeMclass(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeMclass(id)) {
            getMclass();
        } else {
            System.out.print("Error Accured while removing");
        }
 }
 
 Callback<TableColumn<Mclass, String>, TableCell<Mclass, String>> cellFactory = (final TableColumn<Mclass, String> param) -> {
      
         final TableCell<Mclass, String> cell = new TableCell<Mclass, String>() {

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
                         Mclass Mclass = getTableView().getItems().get(getIndex());
                        removeMclass(Mclass.getId()); 
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {  
                      Mclass mclass = getTableView().getItems().get(getIndex());

                        editMclass(mclass);
                    });
                    setGraphic(con);
                    setText(null);
                }
            }

             
     private void editMclass(Mclass mclass) {
              FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("drahmedschool/views/Newclass.fxml"));
            root = (Parent)loader.load();

            NewclassController con = loader.<NewclassController>getController();
            con.setEdit(mclass);
        } catch (IOException ex) {
            Logger.getLogger(MclassesController.class.getName()).log(Level.SEVERE, null, ex);
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
            getMclass();
        });
        stage.show();}
        };
        return cell;
    };
 
}
