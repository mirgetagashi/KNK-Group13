package model;

import java.sql.Date;

public class Teacher extends User{
    private String title;
    private String education;
    private int school_id;
    private int subject_id;

    public Teacher(int id, String firstName, String lastName, String email, String salt, String passwordHash, int address_id, String gender, Date birthday, String title, String education, int school_id, int subject_id) {
        super(id, firstName, lastName, email, salt, passwordHash, address_id, gender, birthday);
        this.title = title;
        this.education = education;
        this.school_id = school_id;
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public String getEducation() {
        return education;
    }

    public int getSchool_id() {
        return school_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getSalt() {
        return super.getSalt();
    }

    @Override
    public String getPasswordHash() {
        return super.getPasswordHash();
    }

    @Override
    public int getAddress_id() {
        return super.getAddress_id();
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    @Override
    public Date getBirthday() {
        return super.getBirthday();
    }
}