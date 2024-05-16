package model;

public class SchoolTable {
    private int id;

    private String name;
    private String city;
    private int numberOfStudents;
    private int numberOfMajors;

    public int getId() {
        return id;
    }

    public int getNumberOfMajors() {
        return numberOfMajors;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public SchoolTable(int id, String name, String city, int numberOfStudents, int numberOfMajors) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.numberOfMajors=numberOfMajors;
        this.numberOfStudents = numberOfStudents;
    }


}
