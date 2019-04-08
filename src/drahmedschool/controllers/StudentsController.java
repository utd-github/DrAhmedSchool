/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
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
public class StudentsController implements Initializable {

    @FXML
    private TableView<Students> studentsTable;
    @FXML
    private TableColumn<Students, String> nocolumn;
    @FXML
    private TableColumn<Students, String> stdname;
    @FXML
    private TableColumn<Students, String> stdphone;
    @FXML
    private TableColumn<Students, String> stdemail;
    @FXML
    private TableColumn<Students, String> stdcyear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getStudents();
    }

    @FXML
    private void addNew(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newstudent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AssignmentsController.class.getName()).log(Level.SEVERE, null, ex);
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
        stage.show();
    }

    public void getStudents() {
        ObservableList<Students> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getStudent();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Students(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("sub_date"),
                        rs.getString("year"),
                        rs.getString("dob"),
                        rs.getString("email")
                ));
            }
            initStudentsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initStudentsTable(ObservableList<Students> Obs) {
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        stdname.setCellValueFactory(new PropertyValueFactory<>("name"));
        stdphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stdemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        stdcyear.setCellValueFactory(new PropertyValueFactory<>("year"));

        studentsTable.setItems(Obs);

    }

}
