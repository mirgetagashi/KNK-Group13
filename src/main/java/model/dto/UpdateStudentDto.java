package model.dto;

import java.sql.Date;
import java.time.LocalDate;

public class UpdateStudentDto {
    private int id;
    private String firstName;
    private String lastName;
    private int address;
    private int school;
    private int major;
    private int level;

    public UpdateStudentDto(int id, String firstName, String lastName, int address, int school, int major, int level) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.school = school;
        this.major = major;
        this.level = level;
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


}
