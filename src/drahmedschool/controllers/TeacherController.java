/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Students;
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
public class TeacherController implements Initializable {

    @FXML
    private TableView<Teachers> teachersTable;
    @FXML
    private TableColumn<Teachers, String> nocolumn;
    @FXML
    private TableColumn<Teachers, String> tname;
    @FXML
    private TableColumn<Teachers, String> tphone;
    @FXML
    private TableColumn<Teachers, String> temail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getTeachers();
    }    


    @FXML
    private void addNew(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newteacher.fxml"));
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
    

    private void getTeachers() {
   
         ObservableList<Teachers> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getTeachers();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Teachers(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("jdate"),
                        rs.getString("des")
                ));
            }
            initTeachersTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTeachersTable(ObservableList<Teachers> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        temail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
       

        teachersTable.setItems(Obs);

    }

    

    
    
}
