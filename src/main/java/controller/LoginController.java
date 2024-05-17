package controller;

import app.Navigator;
import app.SessionManager.AdminSession;
import app.SessionManager.StudentSession;
import app.SessionManager.TeacherSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Administrator;
import model.Students;
import model.Teacher;
import model.dto.LoginUserDto;
import repository.AdminRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
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
        String useremail=this.txtEmail.getText();

        if (useremail.contains("@student")) {
            isLogedIn = StudentService.login(loginUserData);
        } else if (useremail.contains("@teacher")) {
            isLogedIn = TeacherService.login(loginUserData);
        }else if (useremail.contains("@admin")) {
            isLogedIn = AdminService.login(loginUserData);
        } else {
            isLogedIn = false;
        }

        if(isLogedIn){
            if(useremail.contains("@student")){
                Students student= StudentRepository.getByEmail(useremail);
                StudentSession.setStudent(student);
                Navigator.navigate(ae,Navigator.STUDENT_PROFILE);
            }else if(useremail.contains("@teacher")){
                Teacher teacher= TeacherRepository.getByEmail(useremail);
                TeacherSession.setTeacher(teacher);
                Navigator.navigate(ae,Navigator.TEACHER_TABLE);
            }else if(useremail.contains("@admin")){
                Administrator admin= AdminRepository.getByEmail(useremail);
                AdminSession.setAdmin(admin);
                Navigator.navigate(ae,Navigator.ADMIN_PAGE);
            }else {
                Navigator.navigate(ae,Navigator.ADMIN_STUDENT_PAGE);
            }

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
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLoginClick(null); // Simulate a click on the login button
        }
    }

}
