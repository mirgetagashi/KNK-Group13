package model;

public class Grades {
    private int grade_id;
    private Period period;
    private Students student;
    private Subject subject;
    private Teacher teacher;
    private int grade;

    public Grades(int grade_id, Period period, Students student,
                  Subject subject, Teacher teacher, int grade) {
        this.grade_id = grade_id;
        this.period = period;
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public Period getPeriod() {
        return period;
    }

    public Students getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getGrade() {
        return grade;
    }
}
