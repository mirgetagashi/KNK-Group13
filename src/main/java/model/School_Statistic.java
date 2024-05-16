package model;

public class School_Statistic {

    private String name;
    private double avgGrade;

    public School_Statistic(String name, double avgGrade) {
        this.name = name;
        this.avgGrade = avgGrade;
    }

    public String getName() {
        return name;
    }

    public double getAvgGrade() {
        return avgGrade;
    }
}
