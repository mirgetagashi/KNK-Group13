package controller;


import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Address;
import model.Major;
import model.Period;
import model.School;
import model.dto.StudentDto;
import model.dto.UserDto;
import repository.AddressRepository;
import repository.MajorRepository;
import repository.PeriodRepository;
import repository.SchoolRepository;
import service.StudentService;
import service.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private VBox vPane;





    @FXML
    private void handleSignUpClick(ActionEvent ae) {
        Date birthday=java.sql.Date.valueOf(datePickerBirthday.getValue());
        UserDto userSignUpData = UserDto.getInstance(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText(),
                birthday
        );
        boolean response = Validator.validate(userSignUpData);

        if (response) {
            if(this.txtEmail.getText().contains("@student")){
                Navigator.navigateee(vPane, Navigator.NEXT_STUDENT, userSignUpData);
            }else {
                Navigator.navigateee(vPane, Navigator.NEXT_TEACHER, userSignUpData);
            }

        }
    }



    @FXML
    private void handleCancelClick(ActionEvent ae){
    }




}
