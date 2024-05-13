package model;


public class Grades {
    private int grade_id;
    private int level_id;
    private int period1_grade;
    private int perios2_grade;
    private int final_grade;
    private int std_id;
    private int subject_id;
    private int t_id;

    public Grades(int grade_id, int level_id, int period1_grade, int perios2_grade, int final_grade, int std_id, int subject_id, int t_id) {
        this.grade_id = grade_id;
        this.level_id = level_id;
        this.period1_grade = period1_grade;
        this.perios2_grade = perios2_grade;
        this.final_grade = final_grade;
        this.std_id = std_id;
        this.subject_id = subject_id;
        this.t_id = t_id;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public int getPeriod1_grade() {
        return period1_grade;
    }

    public int getPerios2_grade() {
        return perios2_grade;
    }

    public int getFinal_grade() {
        return final_grade;
    }

    public int getStd_id() {
        return std_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public int getT_id() {
        return t_id;
    }
}

