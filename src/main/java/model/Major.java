package model;

public class Major {
    private int id;
    private String major_name;

    public Major(int id, String major_name) {
        this.id = id;
        this.major_name = major_name;
    }

    public int getId() {
        return id;
    }

    public String getMajor_name() {
        return major_name;
    }
}
