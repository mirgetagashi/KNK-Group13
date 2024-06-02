package controller;

import app.Navigator;
import app.SessionManager.StudentSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Students;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentNavigatorController implements Initializable {

    @FXML
    private AnchorPane dashBo;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Label navName;


    @FXML
    void help(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.HELP_PAGE);

    }

    @FXML
    void handleLogOutClick(ActionEvent event) {
        StudentSession.setStudent(null);
        Navigator.navigate(event,Navigator.LOGIN_PAGE);
    }

    @FXML
    void profile(MouseEvent event) {
        Navigator.navigate(dashBo, Navigator.STUDENT_PROFILE);

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


    @FXML
    void handleKeyPressed(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.P) {

            Navigator.navigate(dashBo, Navigator.STUDENT_PROFILE);
        } else if (event.isControlDown() && event.getCode() == KeyCode.D) {
            Navigator.navigate(dashBo,Navigator.STUDENT_CHART);

        }else if (event.isControlDown() && event.getCode() == KeyCode.T) {
            Navigator.navigate(dashBo,Navigator.STUDENT_TABLE);
        }else if (event.isControlDown() && event.getCode() == KeyCode.H) {
            Navigator.navigate(dashBo,Navigator.HELP_PAGE);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Students loggedStudent= StudentSession.getStudent();
        int loggedStudentId=loggedStudent.getId();
        String studentName=loggedStudent.getFirstName();
        String studentSurname=loggedStudent.getLastName();
        String fullName=studentName + " "+studentSurname;
        navName.setText(fullName);



    }
}
