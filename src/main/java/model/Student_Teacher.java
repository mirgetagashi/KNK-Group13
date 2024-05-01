package model;

public class Student_Teacher {
    private Students student;
    private Teacher teacher;
    private Subject subject;
    private Period period;

    public Student_Teacher(Students student, Teacher teacher, Subject subject, Period period) {
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.period = period;
    }

    public Students getStudent() {
        return student;
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
