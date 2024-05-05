package model;

public class City_School {
    private int address_id;
    private int school_id;

    public City_School(int address_id, int school_id) {
        this.address_id = address_id;
        this.school_id = school_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public int getSchool_id() {
        return school_id;
    }
}
