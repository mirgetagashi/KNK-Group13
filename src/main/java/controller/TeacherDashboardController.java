package controller;

import app.SessionManager.TeacherSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Teacher;

import repository.AdminDashboardRepository;
import repository.GradeRepository;
import service.GradeService;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherDashboardController implements Initializable {

    @FXML
    private Label gradesAverage;

    @FXML
    private Label myStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        gradesAverage.setText(String.valueOf(GradeRepository.calculateAverageFinalGrade()));
    }
}
