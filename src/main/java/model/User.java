package model;

import java.sql.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;
    private int address_id;
    private String gender;
    private Date birthday;

    protected User(int id, String firstName, String lastName, String email,
                String salt, String passwordHash, int address, String gender, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address_id = address;
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

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }
}
