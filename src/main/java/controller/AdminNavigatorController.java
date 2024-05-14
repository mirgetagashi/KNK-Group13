/*package controller;

import app.Navigator;
import app.SessionManager.AdminSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminNavigatorController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;


    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    private Label lblTest;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTest.setText(AdminSession.getAdmin().getFirstName());
    }
}*/

package controller;

import app.SessionManager.AdminSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminNavigatorController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblTest;

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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

