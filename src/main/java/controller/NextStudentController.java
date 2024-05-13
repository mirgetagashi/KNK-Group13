package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import model.Address;
import model.Grade_level;
import model.Grades;
import model.Period;
import model.dto.StudentDto;
import model.dto.UserDto;
import repository.*;
import service.StudentService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class NextStudentController implements Initializable {

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private ComboBox<String> periodComboBox;

    @FXML
    private ComboBox<String> schoolComboBox;
    private UserDto userDto;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML
    private RadioButton radioButtonMale;


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
    void handleCancelClick(ActionEvent event) {}



    @FXML
    void handleSignUpClick(ActionEvent event) {
        UserDto userDto = this.userDto;

        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();
        Date birthday= userDto.getBirthday();

        StudentDto userSignUpData = new StudentDto(
                firstName,
                lastName,
                email,
                password,
                confirmPassword,
                AddressRepository.getAddressByCity(cityComboBox.getValue()).getId(),
                SchoolRepository.getSchoolByName(schoolComboBox.getValue()).getId(),
                MajorRepository.getMajorByName(majorComboBox.getValue()).getId(),
                GradeLevelRepository.getLevelByName(periodComboBox.getValue()).getLevel_id(),
                this.getGender(event),
                birthday
        );

        boolean response = StudentService.signUp(userSignUpData);

        if (response) {
            Navigator.navigate(event, Navigator.ADMIN_STUDENT_PAGE);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cityComboBox.setValue("Address");
        schoolComboBox.setValue("School");
        majorComboBox.setValue("Major");
        periodComboBox.setValue("Level");
        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getCity());
        }
        cityComboBox.getItems().addAll(cities);
        cityComboBox.setOnAction(this::handleCitySelection);

        ArrayList<String> levels= new ArrayList<>();
        for(Grade_level level: GradeLevelRepository.getAllLevels()){
            levels.add(level.getLevel_name());
        }
        periodComboBox.getItems().addAll(levels);

    }
    private void handleCitySelection(ActionEvent event) {
        String selectedCity = cityComboBox.getValue();
        ArrayList<String> schools = SchoolRepository.getSchoolByCity(selectedCity);
        schoolComboBox.getItems().addAll(schools);

        schoolComboBox.setOnAction(this::handleSchoolSelection);


    }

    private void handleSchoolSelection(ActionEvent ae){
        String selectedSchool= schoolComboBox.getValue();
        ArrayList<String> majors= MajorRepository.getMajorBySchool(selectedSchool);
        majorComboBox.getItems().addAll(majors);
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }


}
