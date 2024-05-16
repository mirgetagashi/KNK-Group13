package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.School_Statistic;
import repository.AdminDashboardRepository;
import repository.SchoolRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private BarChart<String, Double> chart;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label schoolsNumber;

    @FXML
    private Label studentsNumber;

    @FXML
    private Label teacherNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        schoolsNumber.setText(String.valueOf(AdminDashboardRepository.Schools_Number()));
        studentsNumber.setText(String.valueOf(AdminDashboardRepository.Students_Number()));
        teacherNumber.setText(String.valueOf(AdminDashboardRepository.Teacher_Number()));



        ArrayList<School_Statistic> statistics= new ArrayList<>();
        statistics= AdminDashboardRepository.Schools_Statistic();
        XYChart.Series<String, Double> series= new XYChart.Series<>();
        series.setName("Schools Average Grade");
        for(int i=0;i<statistics.size();i++){
            series.getData().add(new XYChart.Data<>(statistics.get(i).getName(),statistics.get(i).getAvgGrade()));
        }
        chart.getData().add(series);
    }
}
