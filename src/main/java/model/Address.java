package model;

public class Address {
    private int id;
    private String city;
    private String street;

    public Address(int id, String city, String street) {
        this.id = id;
        this.city = city;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
