package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentChartController implements Initializable {

    @FXML
    private PieChart gradeChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = createChartData();
        if (gradeChart != null) {
            gradeChart.getData().addAll(pieChartData);
            formatChartData(pieChartData);
        } else {
            System.err.println("gradeChart is not initialized! Check your FXML bindings.");
        }
    }

    private ObservableList<PieChart.Data> createChartData() {

        return FXCollections.observableArrayList(
                new PieChart.Data("Grade 9", 4),
                new PieChart.Data("Grade 8", 6),
                new PieChart.Data("Grade 10", 4),
                new PieChart.Data("Grade 7", 5),
                new PieChart.Data("Grade 6", 2)
        );
    }

    private void formatChartData(ObservableList<PieChart.Data> pieChartData) {

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty(), " nota")
                )
        );
    }
}
