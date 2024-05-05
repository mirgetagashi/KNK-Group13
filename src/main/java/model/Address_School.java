package model;

public class Address_School {
    private int address_id;
    private int aschool_id;

    public Address_School(int address_id, int aschool_id) {
        this.address_id = address_id;
        this.aschool_id = aschool_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public int getAschool_id() {
        return aschool_id;
    }
}
