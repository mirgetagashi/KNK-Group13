package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class AdminNavigatorController {

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    private AnchorPane pane;

    @FXML
    void handleDashboardClick(ActionEvent event) {
        //Navigator.navigate(pane,Navigator.ADMIN_DASHBOARD_PAGE);

    }

    @FXML
    void handleHelpClick(ActionEvent event) {


    }

    @FXML
    void handleProfileClick(ActionEvent event) {
      //  Navigator.navigate(pane,Navigator.navigate(Navigator.ADMIN_PROFILE););

    }

    @FXML
    void handleStudentsClick(ActionEvent event) {
        Navigator.navigate(pane,Navigator.ADMIN_STUDENT_PAGE);

    }

    @FXML
    void handleTeacherClick(ActionEvent event) {
        Navigator.navigate(pane,Navigator.ADMIN_TEACHER_PAGE);

    }

    @FXML
    void hanldeSchoolsClick(ActionEvent event) {
        Navigator.navigate(pane,Navigator.ADMIN_SCHOOLS_PAGE);

    }




}
