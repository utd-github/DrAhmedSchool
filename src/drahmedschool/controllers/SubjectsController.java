/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Subjects;
import drahmedschool.db.models.Teachers;
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
       

        subjetsTable.setItems(Obs);

    }
    
}
