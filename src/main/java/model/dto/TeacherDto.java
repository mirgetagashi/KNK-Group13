package model.dto;

import model.*;

import java.util.Date;

public class TeacherDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Address address;
    private School school;
    private Subject subject;
    private String  education;
    private String level;
    private Date birthday;
    private String gender;

    public TeacherDto(String firstName, String lastName, String email, String password, String confirmPassword,
                      Address address, School school, Subject subject, String education, String level, Date birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
        this.school = school;
        this.subject = subject;
        this.education = education;
        this.level = level;
        this.birthday = birthday;
        this.gender=gender;
    }

    public String getGender() {
        return gender;
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

    public Subject getSubject() {
        return subject;
    }

    public String getEducation() {
        return education;
    }

    public String getLevel() {
        return level;
    }

    public Date getBirthday() {
        return birthday;
    }
}
