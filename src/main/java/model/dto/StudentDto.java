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

import java.time.LocalDate;
import java.util.Date;

public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private int address;
    private int school;
    private int major;
    private int period;
    private String gender;
    private Date birthday;

    public StudentDto(String firstName, String lastName, String email, String password,
                   String confirmPassword, int city, int  school, int major, int period, String  gender, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address=city;
        this.school=school;
        this.major=major;
        this.period=period;
        this.gender=gender;
        this.birthday=birthday;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String  getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
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

    public int getAddress() {
        return address;
    }

    public int getSchool() {
        return school;
    }

    public int getMajor() {
        return major;
    }

    public int getPeriod() {
        return period;
    }
}
