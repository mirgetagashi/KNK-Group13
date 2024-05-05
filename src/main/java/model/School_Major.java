package model;

public class School_Major {
    private int school_id;
    private int major_id;

    public School_Major(int school_id, int major_id) {
        this.school_id = school_id;
        this.major_id = major_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public int getMajor_id() {
        return major_id;
    }
}
