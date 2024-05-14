package model;

public class Student_Teacher {
    private int student_id;
    private int teacher_id;
    private int subject_id;
    private int period_id;

    public int getStudent_id() {
        return student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public int getPeriod_id() {
        return period_id;
    }

    public Student_Teacher(int student_id, int teacher_id, int subject_id, int period_id) {
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.period_id = period_id;
    }
}
