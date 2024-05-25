package controller;

import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class StudentNavigatorController {

    @FXML
    private AnchorPane dashBo;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    void help(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.HELP_PAGE);

    }

    @FXML
    void profile(MouseEvent event) {

    }

    @FXML
    void settings(MouseEvent event) {

    }

    @FXML
    void student_dashboard(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.STUDENT_CHART);
    }

    @FXML
    void student_profile(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.STUDENT_PROFILE);
    }

    @FXML
    void student_statistics(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.STUDENT_TABLE);

    }

}
