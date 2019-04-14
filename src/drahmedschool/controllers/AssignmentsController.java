/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Assignments;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class AssignmentsController implements Initializable {

    @FXML
    private TableView<Assignments> assignmentsTable;
    @FXML
    private TableColumn<Assignments, String> noColumn;
    @FXML
    private TableColumn<Assignments, String> titleColumn;
    @FXML
    private TableColumn<Assignments, String> issuedColumn;
    @FXML
    private TableColumn<Assignments, String> deadlineColumn;
    @FXML
    private TableColumn<Assignments, String> classNameColumn;
    @FXML
    private TableColumn<Assignments, String> actionsColumn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAssignments();
    }

    @FXML
    private void addNew(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newassignments.fxml"));
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
        stage.setOnHidden(e -> {
            getAssignments();
        });
        stage.show();
    }

    private void getAssignments() {
        ObservableList<Assignments> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getAssignments();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Assignments(
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("class_id"),
                        rs.getString("title"),
                        rs.getString("issued"),
                        rs.getString("deadline"),
                        rs.getString("description")
                ));
            }
            initAssignmentsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initAssignmentsTable(ObservableList<Assignments> Obs) {
        noColumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        classNameColumn.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        issuedColumn.setCellValueFactory(new PropertyValueFactory<>("issued"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        actionsColumn.setCellFactory(cellFactory);
        assignmentsTable.setItems(Obs);

    }

    public void removeAssignment(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeAssignments(id)) {
            getAssignments();
        } else {
            System.out.print("Error Accured while removing");
        }
    }

    Callback<TableColumn<Assignments, String>, TableCell<Assignments, String>> cellFactory = (final TableColumn<Assignments, String> param) -> {
        final TableCell<Assignments, String> cell = new TableCell<Assignments, String>() {

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
                        Assignments assignment = getTableView().getItems().get(getIndex());
                        removeAssignment(assignment.getId());
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
