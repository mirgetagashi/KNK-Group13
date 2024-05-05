package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TeacherSignUpController {

    @FXML
    private ComboBox<String > cityComboBox;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private ComboBox<?> schoolComboBox;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    void handleCancelClick(ActionEvent event) {

    }

    @FXML
    void handleSignUpClick(ActionEvent event) {

    }

}