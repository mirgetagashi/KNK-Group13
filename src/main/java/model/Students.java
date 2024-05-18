package model;

import java.sql.Date;

public class Students extends User{
    private int school;
    private int major;
    private int level;
    public Students(int id, String firstName, String lastName, String email, String salt, String passwordHash,
                    int address, int school, int major, int level, String gender, Date birthday) {
        super(id, firstName, lastName, email, salt, passwordHash, address, gender, birthday);
        this.school = school;
        this.major = major;
        this.level = level;
    }

    public int getId() {
        return super.getId();
    }


    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getSalt() {
        return super.getSalt();
    }

    public String getPasswordHash() {
        return super.getPasswordHash();
    }

    public int getAddress() {
        return super.getAddress();
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
        return super.getGender();
    }

    public Date getBirthday() {
        return super.getBirthday();
    }

}
