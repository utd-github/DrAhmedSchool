/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Events;
import drahmedschool.db.models.Students;
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
public class EventController implements Initializable {

    @FXML
    private TableView<Events> eventsTable;
    @FXML
    private TableColumn<Events, String> nocolumn;
    @FXML
    private TableColumn<Events, String> etitle;
    @FXML
    private TableColumn<Events, String> elocation;
    @FXML
    private TableColumn<Events, String> edate;
    @FXML
    private TableColumn<Events, String> actionsColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getEvents();
    }    

    @FXML
    private void addNew(ActionEvent event) {
           Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newevent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AssignmentsController.class.getName()).log(Level.SEVERE, null, ex);
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
            getEvents();
        });
        stage.show();
    }

    private void getEvents() {
    
        ObservableList<Events> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getEvents();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
               Obs.add(new Events(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("location"),                       
                        rs.getString("edate"),                       
                        rs.getString("des")


                )
               );
            }
            initEventsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initEventsTable(ObservableList<Events> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        etitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        elocation.setCellValueFactory(new PropertyValueFactory<>("location"));       
        edate.setCellValueFactory(new PropertyValueFactory<>("edate"));        
        actionsColumn.setCellFactory(cellFactory);
        eventsTable.setItems(Obs);
    }
    
            public void removeEvents(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeEvents(id)) {
            getEvents();
        } else {
            System.out.print("Error Accured while removing");
        }
    }
    
     Callback<TableColumn<Events, String>, TableCell<Events, String>> cellFactory = (final TableColumn<Events, String> param) -> {
        final TableCell<Events, String> cell = new TableCell<Events, String>() {

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
                        Events events = getTableView().getItems().get(getIndex());
                        removeEvents(events.getId());
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
