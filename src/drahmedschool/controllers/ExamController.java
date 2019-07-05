 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.controllers;

import drahmedschool.db.dbActions;
import drahmedschool.db.dbConnection;
import drahmedschool.db.models.Exams;
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
public class ExamController implements Initializable {

    @FXML
    private TableView<Exams> examsTable;
    @FXML
    private TableColumn<Exams, String> nocolumn;
    @FXML
    private TableColumn<Exams, String> type;
    @FXML
    private TableColumn<Exams, String> edate;
    @FXML
    private TableColumn<Exams, String> des;
    @FXML
    private TableColumn<Exams, String> actionscolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getExams();
    }

    @FXML
    private void addNew(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("drahmedschool/views/Newexam.fxml"));
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
            getExams();
        });
        stage.show();

    }

    private void getExams() {

        ObservableList<Exams> Obs = FXCollections.observableArrayList();
        dbActions action = new dbActions(dbConnection.dbConnect());
        ResultSet rs;
        rs = action.getExams();
        try {
            int no = 0;
            while (rs.next()) {
                no++;
                Obs.add(new Exams(
                        //Database
                        Integer.toString(no),
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getString("eclass"),
                        rs.getString("sid"),
                        rs.getString("edate"),
                        rs.getString("memo")
                ));
            }
            initexamsTable(Obs);

        } catch (SQLException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initexamsTable(ObservableList<Exams> Obs) {

        //Model
        nocolumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        edate.setCellValueFactory(new PropertyValueFactory<>("edate"));
        des.setCellValueFactory(new PropertyValueFactory<>("memo"));

        actionscolumn.setCellFactory(cellFactory);

        examsTable.setItems(Obs);

    }

    public void removeExams(String id) {
        dbActions action = new dbActions(dbConnection.dbConnect());

        if (action.removeExams(id)) {
            getExams();
        } else {
            System.out.print("Error Accured while removing");
        }
    }

    //Edit exam
    public void editexam(Exams exam) {
        FXMLLoader loader = null;
        Parent root = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("drahmedschool/views/Newexam.fxml"));
            root = (Parent)loader.load();

            NewexamController con = loader.<NewexamController>getController();
            con.setEdit(exam);
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
            getExams();
        });
        stage.show();

    }
    
    Callback<TableColumn<Exams, String>, TableCell<Exams, String>> cellFactory = (final TableColumn<Exams, String> param) -> {

        final TableCell<Exams, String> cell = new TableCell<Exams, String>() {

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
                        Exams exam = getTableView().getItems().get(getIndex());
                        removeExams(exam.getId());
                        System.out.println("Deleting ");
                    });

                    ebtn.setOnAction(event -> {
                        Exams exam = getTableView().getItems().get(getIndex());

                        editexam(exam);
                    });
                    setGraphic(con);
                    setText(null);
                }
            }

        };
        return cell;
    };

}
