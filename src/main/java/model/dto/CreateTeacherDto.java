package model.dto;

import model.*;

import java.util.Date;

public class CreateTeacherDto {

    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;

    private Address address;
    private String education;
    private School school;
    private Subject subject;
    private String title;
    private String gender;
    private Date birthday;

    public CreateTeacherDto(String firstName, String lastName, String email, String salt, String passwordHash,
                            Address address, String education, School school, Subject subject, String title, String gender, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address = address;
        this.school = school;
        this.subject = subject;
        this.title = title;
        this.gender = gender;
        this.birthday = birthday;
        this.education=education;
    }

    public String getEducation() {
        return education;
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

    public Address getAddress() {
        return address;
    }

    public School getSchool() {
        return school;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getGender() {
        return gender;
    }

    public java.sql.Date getBirthday() {
        return (java.sql.Date) birthday;
    }
}
