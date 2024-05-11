package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Address;
import model.Period;
import model.dto.StudentDto;
import model.dto.UserDto;
import repository.AddressRepository;
import repository.MajorRepository;
import repository.PeriodRepository;
import repository.SchoolRepository;
import service.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherSignUpController implements Initializable {

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private ComboBox<String> periodComboBox;

    @FXML
    private ComboBox<String> schoolComboBox;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private UserDto userDto;

    @FXML
    void handleCancelClick(ActionEvent event) {}



    @FXML
    void handleSignUpClick(ActionEvent event) {
       /* UserDto userDto = this.userDto;

        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();

        StudentDto userSignUpData = new StudentDto(
                firstName,
                lastName,
                email,
                password,
                confirmPassword,
                AddressRepository.getAddressByCity(cityComboBox.getValue()),
                SchoolRepository.getSchoolByName(schoolComboBox.getValue()),
                MajorRepository.getMajorByName(majorComboBox.getValue()),
                PeriodRepository.getPeriodByName(periodComboBox.getValue())
        );

        boolean response = StudentService.signUp(userSignUpData);

        if (response) {
            Navigator.navigate(event, Navigator.LOGIN_PAGE);
        }*/
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

        ArrayList<String> periods= new ArrayList<>();
        for(Period period: PeriodRepository.getAllPeriods()){
            periods.add(period.getName());
        }
        periodComboBox.getItems().addAll(periods);






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
