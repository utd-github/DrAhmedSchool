/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Events;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
      
        eventsTable.setItems(Obs);
    }
    
}
