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
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("5", 4),
                        new PieChart.Data("4", 6),
                        new PieChart.Data("3",5),
                        new PieChart.Data("6",2));

        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat
                        (data.getName(), "amount: ", data.pieValueProperty())));
        gradeChart.getData().addAll(pieChartData);
    }
}
