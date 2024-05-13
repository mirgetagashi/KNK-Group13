package model;

import java.sql.Date;

public class Students {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;
    private int address;
    private int school;
    private int major;
    private int level;
    private String gender;
    private Date birthday;

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

    public int getAddress() {
        return address;
    }

    public int getSchool() {
        return school;
    }

    public int getMajor() {
        return major;
    }

    public int getLevel() {
        return level;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Students(int id, String firstName, String lastName, String email, String salt, String passwordHash,
                    int address, int school, int major, int level, String gender, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address = address;
        this.school = school;
        this.major = major;
        this.level = level;
        this.gender = gender;
        this.birthday = birthday;
    }
}
