package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Students;
import service.StudentService;

import java.sql.SQLException;

public class SettingsStudentController {

    @FXML
    private ChoiceBox<?> choiceBox;

    @FXML
    private PasswordField confirmNewPwd;

    @FXML
    private PasswordField newPwd;

    @FXML
    private PasswordField oldPwd;

    @FXML
    void handleCancel(ActionEvent event) {

    }


    @FXML
    void handleSave(ActionEvent event) throws SQLException {

    }


}



