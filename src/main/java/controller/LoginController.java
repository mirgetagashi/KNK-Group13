package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.dto.LoginUserDto;
import service.AdminService;
import service.StudentService;
import service.TeacherService;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private void handleLoginClick(ActionEvent ae){
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );
        boolean isLogedIn;

        if (this.txtEmail.getText().contains("@student")) {
            isLogedIn = StudentService.login(loginUserData);
        } else if (this.txtEmail.getText().contains("@teacher")) {
            isLogedIn = TeacherService.login(loginUserData);
        }else if (this.txtEmail.getText().contains("@admin")) {
            isLogedIn = AdminService.login(loginUserData);
        } else {
            isLogedIn = false;
        }

        if(!isLogedIn){
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        }

    }
    @FXML
    private void handleCancelClick(ActionEvent ae){

    }
    @FXML
    private void handleCreateAccountClick(MouseEvent me){
        Navigator.navigate(me, Navigator.SIGN_UP_PAGE);
    }
    @FXML
    private void handleForgotPasswordClick(MouseEvent me){

    }

}
