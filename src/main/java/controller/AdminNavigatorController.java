package controller;

import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class AdminNavigatorController {

    @FXML
    private AnchorPane dashboardPane;


    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    void handleDashboardClick(MouseEvent event) {

    }

    @FXML
    void handleHelpClick(MouseEvent event) {

    }

    @FXML
    void handleProfileClick(MouseEvent event) {

    }

    @FXML
    void handleSettingsClick(MouseEvent event) {

    }

    @FXML
    void handleStudentsClick(MouseEvent event) {
        Navigator.navigate(dashboardPane,Navigator.ADMIN_STUDENT_PAGE);

    }



}
