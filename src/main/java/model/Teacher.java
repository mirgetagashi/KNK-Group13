package model;

import java.util.Date;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;
    private int address_id;
    private String title;
    private String education;
    private String school_id;
    private int subject_id;
    private String gender;
    private Date birthday;

    public Teacher(int id, String firstName, String lastName, String email, String salt, String passwordHash, int address_id, String title, String education, String school_id, int subject_id, String gender, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address_id = address_id;
        this.title = title;
        this.education = education;
        this.school_id = school_id;
        this.subject_id = subject_id;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
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

    public int getAddress_id() {
        return address_id;
    }

    public String getTitle() {
        return title;
    }

    public String getEducation() {
        return education;
    }

    public String getSchool_id() {
        return school_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }
}