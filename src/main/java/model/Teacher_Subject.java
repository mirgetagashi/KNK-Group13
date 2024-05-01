package model;

public class Teacher_Subject {
    private Teacher teacher;
    private Subject subject;
    private Period period;

    public Teacher_Subject(Teacher teacher, Subject subject, Period period) {
        this.teacher = teacher;
        this.subject = subject;
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public Period getPeriod() {
        return period;
    }
}
