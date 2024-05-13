package model;

public class Grades_Teacher_Subject {
    private String teacherFirstName;
    private String teacherLastName;
    private int subjectId;
    private String subjectName;
    private int firstPeriodGrade;
    private int secondPeriodGrade;
    private int finalGrade;

    public Grades_Teacher_Subject(String teacherFirstName, String teacherLastName, int subjectId, String subjectName, int firstPeriodGrade, int secondPeriodGrade, int finalGrade) {
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.firstPeriodGrade = firstPeriodGrade;
        this.secondPeriodGrade = secondPeriodGrade;
        this.finalGrade = finalGrade;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getFirstPeriodGrade() {
        return firstPeriodGrade;
    }

    public int getSecondPeriodGrade() {
        return secondPeriodGrade;
    }

    public int getFinalGrade() {
        return finalGrade;
    }
}
