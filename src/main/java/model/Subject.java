package model;

public class Subject {
    private int id;
    private String name;
    private int address_id;

    public Subject(int id, String name, int address_id) {
        this.id = id;
        this.name = name;
        this.address_id=address_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
