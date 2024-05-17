package controller;

import app.Navigator;
import app.SessionManager.TeacherSession;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Grade_level;
import model.Grades;
;
import model.dto.TeacherTableDto;

import model.dto.UpdateGradeDto;
import repository.GradeLevelRepository;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.GradeRepository;
import repository.TeacherTableRepository;
import service.GradeService;

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
        boolean response= GradeService.addGrade(teacherTableDto);
        if (response) {
            Navigator.navigate(ae, Navigator.TEACHER_TABLE);
        }
    }

    @FXML
    private void handleDeleteClick(ActionEvent event){
        {

            Grades selectedGrade = studentTable.getSelectionModel().getSelectedItem();


            if (selectedGrade != null) {

                boolean deleted = GradeRepository.deleteGrade(selectedGrade.getGrade_id());


                if (deleted) {

                    studentTable.getItems().remove(selectedGrade);
                    System.out.println("The row has been successfully deleted.");
                    studentTable.getSelectionModel().clearSelection();
                    clearForm();

                } else {
                    System.out.println( "Error during row deletion.");
                }
            } else {
                System.out.println("Please select a row to delete.");
            }
        }

    }

    @FXML
    private void handleUpdateClick(ActionEvent ae) {

        int studentId = Integer.parseInt(txtStudentId.getText());
        String level = levelComboBox.getValue();
        int period1Grade = period1GradeSpinner.getValue();
        int period2Grade = period2GradeSpinner.getValue();

        Grades selectedGrade = studentTable.getSelectionModel().getSelectedItem();

        if (selectedGrade != null) {

            UpdateGradeDto updatedGradeDto = new UpdateGradeDto(selectedGrade.getGrade_id(), selectedGrade.getLevel_id(),
                    period1Grade, period2Grade,
                    selectedGrade.getFinal_grade(), studentId,
                    selectedGrade.getSubject_id(), selectedGrade.getT_id());

            boolean updated = GradeRepository.updateGrade(updatedGradeDto);

            if (updated) {

                ObservableList<Grades> updatedGradesList = FXCollections.observableArrayList(GradeRepository.getAllGrades());
                studentTable.setItems(updatedGradesList);
                System.out.println("Grade successfully updated.");
                clearForm();
            } else {
                System.out.println("Error updating grade.");
            }
        } else {
            System.out.println("Please select a grade to update.");
        }
    }

    //I largon te dhenat nga forma sa here qe bohet grade update ose grade delete
    private void clearForm() {
        txtStudentId.clear();
        levelComboBox.getSelectionModel().clearSelection();
        period1GradeSpinner.getValueFactory().setValue(1);
        period2GradeSpinner.getValueFactory().setValue(1);
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

        ObservableList<Grades> grades = FXCollections.observableArrayList(TeacherTableRepository.getGradesTable());
        if (studentTable != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("std_id"));
            levelColumn.setCellValueFactory(new PropertyValueFactory<>("level_id"));
            period1GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period1_grade"));
            period2GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period2_grade"));
            finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("final_grade"));

            studentTable.setItems(grades);

            //mbushet forma me te dhenat e rreshtit qe eshte selektu
            studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    txtStudentId.setText(String.valueOf(newSelection.getStd_id()));

                    // merr emrin e levelit qe eshte selektu permes Id
                    Grade_level selectedLevel = GradeLevelRepository.getLevelById(newSelection.getLevel_id());
                    if (selectedLevel != null) {
                        levelComboBox.setValue(selectedLevel.getLevel_name());
                    }

                    period1GradeSpinner.getValueFactory().setValue(newSelection.getPeriod1_grade());
                    period2GradeSpinner.getValueFactory().setValue(newSelection.getPeriod2_grade());
                }
            });
        } else {
            System.out.println("GradesTable is null.");
        }



    }

}