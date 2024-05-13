package controller;


import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Address;
import model.Major;
import model.Period;
import model.School;
import model.dto.StudentDto;
import repository.AddressRepository;
import repository.MajorRepository;
import repository.PeriodRepository;
import repository.SchoolRepository;
import service.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentSignUpController implements Initializable{
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
    private ComboBox<String> cityComboBox;
    @FXML
    private ComboBox<String> schoolComboBox;
    @FXML
    private ComboBox<String> majorComboBox;
    @FXML
    private ComboBox<String> periodComboBox;


    @FXML
    private void handleSignUpClick(ActionEvent ae){
   /*     StudentDto userSignUpData = new StudentDto(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText(),
                AddressRepository.getAddressByCity(cityComboBox.getValue()).getId(),
                SchoolRepository.getSchoolByName(schoolComboBox.getValue()).getId(),
                MajorRepository.getMajorByName(majorComboBox.getValue()).getId(),
                PeriodRepository.getPeriodByName(periodComboBox.getValue()).getId()
        );
        boolean response = StudentService.signUp(userSignUpData);

        if(response){
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        }*/

    }
    @FXML
    private void handleCancelClick(ActionEvent ae){
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



}