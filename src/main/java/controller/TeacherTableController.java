package controller;

import app.SessionManager.TeacherSession;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Grade_level;
import model.Grades;

import model.dto.TeacherTableDto;
import repository.GradeLevelRepository;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.GradeRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherTableController implements Initializable {


    @FXML
    private AnchorPane addgradeForm;
    @FXML
    private TextField txtStudentId;

    @FXML
    private ComboBox<String> levelComboBox;

    @FXML
    private Spinner<Integer> period1GradeSpinner;

    @FXML
    private Spinner<Integer> period2GradeSpinner;

    @FXML
    private TableView<Grades> studentTable;

    @FXML
    private TableColumn<Grades, Integer> studentIdColumn;

    @FXML
    private TableColumn<Grades, String> levelColumn;

    @FXML
    private TableColumn<Grades, Integer>period1GradeColumn ;

    @FXML
    private TableColumn<Grades, Integer> period2GradeColumn;

    @FXML
    private TableColumn<Grades, Integer> finalGradeColumn;


    @FXML
    private void handleAddGradeClick (ActionEvent ae){
        Integer studentId = Integer.parseInt(this.txtStudentId.getText());
        TeacherTableDto teacherTableDto =new TeacherTableDto(
                TeacherSession.getTeacher().getId(),
                TeacherSession.getTeacher().getSubject_id(),
                studentId,
                this.levelComboBox.getValue(),
                this.period1GradeSpinner.getValue(),
                this.period2GradeSpinner.getValue()

        );


    };
    @FXML
    private void handleUpdateClick(ActionEvent ae){

    }
    @FXML
    private void handleDeleteClick(ActionEvent ae){

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArrayList<String> levels= new ArrayList<>();
        for(Grade_level level: GradeLevelRepository.getAllLevels()){
            levels.add(level.getLevel_name());
        }
        levelComboBox.getItems().addAll(levels);



        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        valueFactory.setValue(1);
        period1GradeSpinner.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        valueFactory2.setValue(1);
        period2GradeSpinner.setValueFactory(valueFactory2);

        ObservableList<Grades> grades = FXCollections.observableArrayList(GradeRepository.getAllGrades());
        if (studentTable != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
            period1GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period1Grade"));
            period2GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period2Grade"));
            finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));

            studentTable.setItems(grades);
        } else {
            System.out.println("studentTable is null.");
        }



    }



}
