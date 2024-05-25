package controller;

import app.Navigator;
import app.SessionManager.AdminSession;
import app.SessionManager.StudentSession;
import app.SessionManager.TeacherSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Administrator;
import model.Students;
import model.Teacher;
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
    private Label lblErrorMessage; // Add this line

    @FXML
    private void handleCancelClick(ActionEvent ae) {
    }

    @FXML
    private void handleForgotPasswordClick(MouseEvent me) {
    }

    @FXML
    private void handleLoginClick(ActionEvent ae) {
        lblErrorMessage.setText(""); // Clear error message
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );
        boolean isLoggedIn;
        String useremail = this.txtEmail.getText();

        if (useremail.contains("@student")) {
            isLoggedIn = StudentService.login(loginUserData);
        } else if (useremail.contains("@teacher")) {
            isLoggedIn = TeacherService.login(loginUserData);
        } else if (useremail.contains("@admin")) {
            isLoggedIn = AdminService.login(loginUserData);
        } else {
            isLoggedIn = false;
        }

        if (isLoggedIn) {
            if (useremail.contains("@student")) {
                Students student = StudentService.getByEmail(useremail);
                StudentSession.setStudent(student);
                Navigator.navigate(ae, Navigator.STUDENT_NAVIGATOR);
            } else if (useremail.contains("@teacher")) {
                Teacher teacher = TeacherService.getByEmail(useremail);
                TeacherSession.setTeacher(teacher);
                Navigator.navigate(ae, Navigator.TEACHER_NAVIGATOR);
            } else {
                Administrator admin = AdminService.getByEmail(useremail);
                AdminSession.setAdmin(admin);
                Navigator.navigate(ae, Navigator.ADMIN_PAGE);
            }
        } else {
            lblErrorMessage.setText("Incorrect email or password!"); // Set error message
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginUser(event);
        }
    }

    private void loginUser(KeyEvent event) {
        lblErrorMessage.setText(""); // Clear error message
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );
        boolean isLoggedIn;
        String useremail = this.txtEmail.getText();

        if (useremail.contains("@student")) {
            isLoggedIn = StudentService.login(loginUserData);
        } else if (useremail.contains("@teacher")) {
            isLoggedIn = TeacherService.login(loginUserData);
        } else if (useremail.contains("@admin")) {
            isLoggedIn = AdminService.login(loginUserData);
        } else {
            isLoggedIn = false;
        }

        if (isLoggedIn) {
            if (useremail.contains("@student")) {
                Students student = StudentService.getByEmail(useremail);
                StudentSession.setStudent(student);
                Navigator.navigate(event, Navigator.STUDENT_NAVIGATOR);
            } else if (useremail.contains("@teacher")) {
                Teacher teacher = TeacherService.getByEmail(useremail);
                TeacherSession.setTeacher(teacher);
                Navigator.navigate(event, Navigator.TEACHER_NAVIGATOR);
            } else {
                Administrator admin = AdminService.getByEmail(useremail);
                AdminSession.setAdmin(admin);
                Navigator.navigate(event, Navigator.ADMIN_PAGE);
            }
        } else {
            lblErrorMessage.setText("Incorrect email or password!"); // Set error message
        }
    }
}
