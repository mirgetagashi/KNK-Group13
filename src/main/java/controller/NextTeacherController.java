package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Address;
import model.School;
import model.Subject;
import model.dto.StudentDto;
import model.dto.TeacherDto;
import model.dto.UserDto;
import repository.*;
import service.StudentService;
import service.TeacherService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class NextTeacherController implements Initializable {

    @FXML
    private ComboBox<String> cityComboBox;


    @FXML
    private ComboBox<String> schoolComboBox;

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private ComboBox<String> titleComboBox;

    @FXML
    private TextField txtEducation;
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

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }



    @FXML
    void handleCancelClick(ActionEvent event) {

    }

    @FXML
    void handleSignUpClick(ActionEvent event) {
        UserDto userDto = this.userDto;

        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();
        Date birthday= userDto.getBirthday();

        TeacherDto userSignUpData = new TeacherDto(
                firstName,
                lastName,
                email,
                password,
                confirmPassword,
                AddressRepository.getAddressByCity(cityComboBox.getValue()),
                SchoolRepository.getSchoolByName(schoolComboBox.getValue()),
                SubjectRepository.getSubjectByName(subjectComboBox.getValue()),
                this.txtEducation.getText(),
                titleComboBox.getValue(),
                birthday,
                this.getGender(event)
        );

        boolean response = TeacherService.signUp(userSignUpData);

        if (response) {
            Navigator.navigate(event, Navigator.LOGIN_PAGE);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityComboBox.setValue("Address");
        schoolComboBox.setValue("School");
        subjectComboBox.setValue("Subject");
        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getCity());
        }
        cityComboBox.getItems().addAll(cities);
        cityComboBox.setOnAction(this::handleCitySelection);

        ArrayList<String > subjects= new ArrayList<>();
        for(Subject subject: SubjectRepository.getAllSubjects()){
            subjects.add(subject.getName());
        }
        subjectComboBox.getItems().addAll(subjects);

        String[] titles={"Bsc","Msc","Phd"};
        titleComboBox.getItems().addAll(titles);
    }



    private void handleCitySelection(ActionEvent event) {
        String selectedCity = cityComboBox.getValue();
        ArrayList<String> schools = SchoolRepository.getSchoolByCity(selectedCity);
        schoolComboBox.getItems().addAll(schools);


    }


}
