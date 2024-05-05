package model.dto;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Address;
import model.Major;
import model.Period;
import model.School;

public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Address address;
    private School school;
    private Major major;
    private Period period;

    public StudentDto(String firstName, String lastName, String email, String password,
                   String confirmPassword, Address city, School  school, Major major, Period period) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address=city;
        this.school=school;
        this.major=major;
        this.period=period;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Address getAddress() {
        return address;
    }

    public School getSchool() {
        return school;
    }

    public Major getMajor() {
        return major;
    }

    public Period getPeriod() {
        return period;
    }
}
