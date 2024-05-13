package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Students;
import repository.StudentRepository;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminStudentController implements  Initializable{


    @FXML
    private TableColumn<Students, String> columnStudentEmail;

    @FXML
    private TableColumn<Students, String> columnStudentLastName;

    @FXML
    private TableColumn<Students, Integer> columnStudentID;

    @FXML
    private TableColumn<Students, String> columnStudentName;

    @FXML
    private TableColumn<Students, Integer> columnsStudentAddress;

    @FXML
    private TableColumn<Students, Integer > columnsStudentLevel;

    @FXML
    private TableColumn<Students, Integer> columnsStudentMajor;

    @FXML
    private TableColumn<Students, Integer> columnsStudentSchool;


    @FXML
    void handleAddClick(ActionEvent event) {

    }

    @FXML
    void handleDeleteClick(ActionEvent event) {

    }

    @FXML
    void handleEditClick(ActionEvent event) {

    }

    @FXML
    private TableView<Students> StudentTable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Students> students = FXCollections.observableArrayList(StudentRepository.getAllStudents());
        if (StudentTable != null) {
            columnStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnsStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            columnsStudentMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
            columnsStudentLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
            columnsStudentSchool.setCellValueFactory(new PropertyValueFactory<>("school"));

            StudentTable.setItems(students);
        } else {
            System.out.println("StudentTable is null.");
        }
    }

}
