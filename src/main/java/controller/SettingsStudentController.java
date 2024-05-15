package controller;
import app.Navigator;
import app.SessionManager.StudentSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Students;
import model.dto.ChangePasswordRequestDto;
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
        ChangePasswordRequestDto userData= new ChangePasswordRequestDto(
                StudentSession.getStudent().getId(),
                this.oldPwd.getText(),
                this.newPwd.getText(),
                this.confirmNewPwd.getText(),
                StudentSession.getStudent().getPasswordHash(),
                StudentSession.getStudent().getSalt()
        );
        boolean request=StudentService.changePassword(userData);

        if(request){
            Navigator.navigate(event,Navigator.LOGIN_PAGE);
        }
    }
}



