package model;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String salt;
    private String passwordHash;
    private Address address;

    public Teacher(int id, String firstName, String lastName, String email,
                   String username, String salt, String passwordHash, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.address = address;
    }
}
