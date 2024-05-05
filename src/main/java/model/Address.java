package model;

public class Address {
    private int id;
    private String city;

    public Address(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

}
