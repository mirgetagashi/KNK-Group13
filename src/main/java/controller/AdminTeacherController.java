package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Students;
import model.Teacher;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminTeacherController implements Initializable {

    @FXML
    private TableView<Teacher> TeachersTable;

    @FXML
    private TableColumn<Teacher, Integer> columnAddress;

    @FXML
    private TableColumn<Teacher, Date> columnBirthday;

    @FXML
    private TableColumn<Teacher, String> columnEmail;

    @FXML
    private TableColumn<Teacher, String > columnFirstName;

    @FXML
    private TableColumn<Teacher, Integer> columnID;

    @FXML
    private TableColumn<Teacher, String> columnLastName;

    @FXML
    private TableColumn<Teacher, String> columnSchool;

    @FXML
    private TableColumn<Teacher, String> columnSubject;

    @FXML
    private TableColumn<Teacher, String> columnTitle;

    @FXML
    void handleAddClick(ActionEvent event) {

    }

    @FXML
    void handleDeleteClick(ActionEvent event) {

    }

    @FXML
    void handleEditClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Teacher> teachers = FXCollections.observableArrayList(TeacherRepository.getAllTeachers());
        if (TeachersTable != null) {
            columnAddress.setCellValueFactory(new PropertyValueFactory<>("address_id"));
            columnBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
            columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnSubject.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
            columnSchool.setCellValueFactory(new PropertyValueFactory<>("school_id"));
            columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

            TeachersTable.setItems(teachers);
        } else {
            System.out.println("StudentTable is null.");
        }
    }

}
