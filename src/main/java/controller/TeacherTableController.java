package controller;

import app.Navigator;
import app.SessionManager.TeacherSession;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Grade_level;
import model.Grades;
import model.dto.TeacherGradeDeleteDto;
import model.dto.TeacherGradeUpdateDto;
import model.dto.TeacherTableDto;



import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


import service.GradeLevelService;
import service.GradeService;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
    private TableColumn<Grades, Integer> period1GradeColumn;
    @FXML
    private TableColumn<Grades, Integer> period2GradeColumn;
    @FXML
    private TableColumn<Grades, Integer> finalGradeColumn;
    @FXML
    private Label messageLabel;

    @FXML
    private Pagination pagination;
    private final static int rowsPerPage = 15;

    private ObservableList<Grades> dataList;
    @FXML
    private void handleAddGradeClick(ActionEvent ae) {
        int levelID=returnId(levelComboBox.getValue());

        Integer studentId = Integer.parseInt(this.txtStudentId.getText());
        TeacherTableDto teacherTableDto = new TeacherTableDto(
                TeacherSession.getTeacher().getId(),
                TeacherSession.getTeacher().getSubject_id(),
                studentId,
                levelID,
                this.period1GradeSpinner.getValue(),
                this.period2GradeSpinner.getValue()
        );
        boolean response = GradeService.addGrade(teacherTableDto);
        if (response) {
//            showMessage("Grade added successfully.","succes");
            Navigator.navigate(ae, Navigator.TEACHER_TABLE);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Grade Added Successfully");
            alert.setContentText("The grade has been added successfully.");
            alert.showAndWait();
        } else {
            showMessage("Student ID does not exist.", "error");
        }
    }

    @FXML
    private void handleDeleteClick(ActionEvent event) {
        Grades selectedGrade = studentTable.getSelectionModel().getSelectedItem();
        if (selectedGrade != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this grade?");
            alert.setContentText("This action cannot be undone.");

            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                TeacherGradeDeleteDto teacherGradeDeleteDto=new TeacherGradeDeleteDto(
                        selectedGrade.getGrade_id(),
                        selectedGrade.getStd_id(),
                        TeacherSession.getTeacher().getSubject_id(),
                        TeacherSession.getTeacher().getId(),
                        selectedGrade.getLevel_id(),
                        selectedGrade.getPeriod1_grade(),
                        selectedGrade.getPeriod2_grade()


                );
                boolean response = GradeService.deleteGrade(teacherGradeDeleteDto);
                if (response) {
                    studentTable.getItems().remove(selectedGrade);
                    showMessage("Grade deleted successfully.", "success");
                    studentTable.getSelectionModel().clearSelection();
                    clearForm();
                } else {
                    showMessage("An error occurred while deleting the grade.", "error");
                }
            }
            else {

                clearForm();
                studentTable.getSelectionModel().clearSelection();
            }
        }
        else {
            showMessage("Please select a row to delete.", "error");
        }
    }



    @FXML
    private void handleUpdateClick(ActionEvent ae) {

        int studentId = Integer.parseInt(txtStudentId.getText());

        int period1Grade = period1GradeSpinner.getValue();
        int period2Grade = period2GradeSpinner.getValue();

        Grades selectedGrade = studentTable.getSelectionModel().getSelectedItem();
        if (selectedGrade != null) {
            TeacherGradeUpdateDto  teacherGradeUpdateDto= new TeacherGradeUpdateDto(
                    selectedGrade.getGrade_id(),
                    studentId,
                    selectedGrade.getSubject_id(),
                    selectedGrade.getT_id(),
                    selectedGrade.getLevel_id(),
                    period1Grade,
                    period2Grade




            );

            boolean updated = GradeService.updateGrade(teacherGradeUpdateDto);
            if (updated) {
                ObservableList<Grades> updatedGradesList = FXCollections.observableArrayList(GradeService.getAllGrades());
                studentTable.setItems(updatedGradesList);
                clearForm();
                showMessage("Grade updated successfully.", "success");

            } else {
                showMessage("There is nothing to update.", "error");
            }
        }
        else {
            showMessage("Please select a grade to update.", "error");
        }
    }


    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        if ("success".equals(type)) {
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
        }
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> messageLabel.setText(""));
        visiblePause.play();

    }
    private void clearForm() {
        txtStudentId.clear();
        levelComboBox.getSelectionModel().clearSelection();
        levelComboBox.setValue(null);
        period1GradeSpinner.getValueFactory().setValue(1);
        period2GradeSpinner.getValueFactory().setValue(1);


    }
    private TableView<Grades> createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
        studentTable.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
        return studentTable;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArrayList<String> levels = new ArrayList<>();
        for (Grade_level level : GradeLevelService.getAllLevels()) {
            levels.add(level.getLevel_id() + " " + level.getLevel_name());
        }
        levelComboBox.getItems().addAll(levels);

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        valueFactory.setValue(1);
        period1GradeSpinner.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        valueFactory2.setValue(1);
        period2GradeSpinner.setValueFactory(valueFactory2);

        if (studentTable != null) {
            studentTable.setRowFactory(tv -> {
                TableRow<Grades> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        if (row.isSelected()) {
                            studentTable.getSelectionModel().clearSelection();
                            clearForm();
                        }
                    }
                });
                return row;
            });

            dataList = FXCollections.observableArrayList(GradeService.getGradesTable());
            if (studentTable != null) {
                studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("std_id"));
                levelColumn.setCellValueFactory(new PropertyValueFactory<>("level_id"));
                period1GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period1_grade"));
                period2GradeColumn.setCellValueFactory(new PropertyValueFactory<>("period2_grade"));
                finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("final_grade"));


                pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));
                pagination.setPageFactory(this::createPage);





                studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtStudentId.setText(String.valueOf(newSelection.getStd_id()));
                        Grade_level selectedLevel = GradeLevelService.getLevelById(newSelection.getLevel_id());
                        if (selectedLevel != null) {
                            levelComboBox.setValue(selectedLevel.getLevel_name());
                        }
                        period1GradeSpinner.getValueFactory().setValue(newSelection.getPeriod1_grade());
                        period2GradeSpinner.getValueFactory().setValue(newSelection.getPeriod2_grade());
                        txtStudentId.setEditable(false);
                        levelComboBox.setDisable(true);
                    } else {
                        txtStudentId.setEditable(true);
                        levelComboBox.setDisable(false);
                    }
                });
            } else {
                System.out.println("GradesTable is null.");
            }
        }


    }
    private int returnId(String select){
        String[] vargu=select.split(" ");
        int id=Integer.parseInt(vargu[0]);
        return id;
    }

}

