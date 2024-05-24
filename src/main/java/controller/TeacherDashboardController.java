package controller;

import app.SessionManager.TeacherSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Teacher;

import repository.AdminDashboardRepository;
import repository.GradeRepository;
import repository.TeacherDashboardRepository;
import service.GradeService;
import service.TeacherDashboardService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherDashboardController implements Initializable {

    @FXML
    private Label gradesAverage;

    @FXML
    private Label myStudents;

    @FXML
    private PieChart gradesPieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher loggedInTeacher = TeacherSession.getTeacher();
        int loggedInTeacherId = loggedInTeacher.getId();

        double averageGrade = TeacherDashboardService.calculateAverageFinalGradeByTeacherId(loggedInTeacherId);
        gradesAverage.setText(String.format("%.2f", averageGrade));


        List<Integer> gradeList = TeacherDashboardService.getGradesByTeacherId(loggedInTeacherId);


        int[] gradeCounts = new int[6];
        for (int grade : gradeList) {
            gradeCounts[grade]++;
        }

        List<PieChart.Data> pieChartData = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            pieChartData.add(new PieChart.Data("Grade " + i, gradeCounts[i]));
        }

        gradesPieChart.getData().addAll(pieChartData);
    }
}
