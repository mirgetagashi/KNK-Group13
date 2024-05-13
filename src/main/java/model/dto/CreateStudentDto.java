package model.dto;

import model.Address;
import model.Major;
import model.Period;
import model.School;

import java.util.Date;

public class CreateStudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;

    private int address;
    private int school;
    private int major;
    private int period;
    private String gender;
    private Date birthday;

    public CreateStudentDto(String firstName, String lastName, String email,
                         String salt, String passwordHash, int city, int school,
                         int major, int period, String gender, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address = city;
        this.school = school;
        this.major = major;
        this.period = period;
        this.gender=gender;
        this.birthday=birthday;
    }

    public String getGender() {
        return gender;
    }

    public java.sql.Date getBirthday() {
        return (java.sql.Date) birthday;
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

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
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
