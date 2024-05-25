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
        Navigator.navigate(changePane, Navigator.ADMIN_DASHBOARD);


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
        Navigator.navigate(changePane, Navigator.ADMIN_STUDENT_PAGE);

    }

    @FXML
    void handleTeacherClick(ActionEvent event) {
        Navigator.navigate(changePane,Navigator.ADMIN_TEACHER_PAGE);

    }



    @FXML
    void profile(MouseEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminName.setText(AdminSession.getAdmin().getFirstName()+" "+AdminSession.getAdmin().getLastName());
    }
}
