package controller;

import app.Navigator;
import app.SessionManager.AdminSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminNavigatorController implements Initializable {

    @FXML
    private Label adminName;

    @FXML
    private AnchorPane changePane;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    void handleDashboardClick(ActionEvent event) {

    }

    @FXML
    void handleHelpClick(ActionEvent event) {

    }

    @FXML
    void handleLogOutClick(ActionEvent event) {
        AdminSession.setAdmin(null);
        Navigator.navigate(event,Navigator.LOGIN_PAGE);
    }

    @FXML
    void handleStudentsClick(ActionEvent event) {

    }

    @FXML
    void handleTeacherClick(ActionEvent event) {

    }

    @FXML
    void help(MouseEvent event) {

    }

    @FXML
    void profile(MouseEvent event) {

    }

    @FXML
    void student_dashboard(MouseEvent event) {

    }

    @FXML
    void student_statistics(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminName.setText(AdminSession.getAdmin().getFirstName()+" "+AdminSession.getAdmin().getLastName());
    }
}
