package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import repository.StudentRepository;
import repository.StudentTableRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable{

    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> finalGrade;

    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> firstPeriodGrade;

    @FXML
    private TableColumn<Grades_Teacher_Subject, String> profFirstName;

    @FXML
    private TableColumn<Grades_Teacher_Subject, String> profLastName;

    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> secondPeriodGrade;

    @FXML
    private TableView<Grades_Teacher_Subject> studentTable;

    @FXML
    private TableColumn<Grades_Teacher_Subject, String> subject;

    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> subjectId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Grades_Teacher_Subject> gts = FXCollections.observableArrayList(StudentTableRepository.getStudentsTable(1));
        if (studentTable != null) {
            subjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
            subject.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
            secondPeriodGrade.setCellValueFactory(new PropertyValueFactory<>("secondPeriodGrade"));
            firstPeriodGrade.setCellValueFactory(new PropertyValueFactory<>("firstPeriodGrade"));
            profFirstName.setCellValueFactory(new PropertyValueFactory<>("teacherFirstName"));
            profLastName.setCellValueFactory(new PropertyValueFactory<>("teacherLastName"));
            finalGrade.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));


            studentTable.setItems(gts);
        } else {
            System.out.println("StudentTable is null.");
        }
    }


}
