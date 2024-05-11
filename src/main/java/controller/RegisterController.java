package controller;


import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private RadioButton radioButtonFemale;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private DatePicker datePickerBirthday;




    public String getGender(ActionEvent ae){
        String  genderSelect;
        if(radioButtonMale.isSelected()){
            genderSelect="M";
        }else if(radioButtonFemale.isSelected()){
            genderSelect="F";
        }else {
            genderSelect="";
        }
        return genderSelect;
    }


    @FXML
    private void handleSignUpClick(ActionEvent ae) {
        Date birthday=java.sql.Date.valueOf(datePickerBirthday.getValue());
        UserDto userSignUpData = UserDto.getInstance(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText(),
                this.getGender(ae),
                birthday
        );
        boolean response = Validator.validate(userSignUpData);

        if (response) {
            if(this.txtEmail.getText().contains("@student")){
                Navigator.navigateWithUserDto(ae, Navigator.NEXT_STUDENT, userSignUpData);
            }else {
                Navigator.navigateWithUserDto(ae, Navigator.NEXT_TEACHER, userSignUpData);
            }

        }
    }



    @FXML
    private void handleCancelClick(ActionEvent ae){
    }




}
