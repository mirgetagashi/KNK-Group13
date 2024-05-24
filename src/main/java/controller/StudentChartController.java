package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import service.StudentService;

import java.util.Map;

public class StudentChartController {

    @FXML
    private Label studentNameLabel;

    @FXML
    private LineChart<String, Number> gradesLineChart;

    @FXML
    private PieChart gradeChart;

    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }

    public void initialize() {
        if (loggedInUsername != null) {
            studentNameLabel.setText("Grades for Student: " + loggedInUsername);
            loadStudentGrades(loggedInUsername);
        }
    }

    private void loadStudentGrades(String username) {
        Map<String, Integer> grades = StudentService.getGradesByStudent(username);

        // Populate PieChart
        gradeChart.getData().clear();
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            gradeChart.getData().add(slice);
        }

        // Populate LineChart
        gradesLineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        gradesLineChart.getData().add(series);
    }
}
