package model.dto;

public class TeacherGradeDeleteDto {
    private int gradeId;
    private int studentId;
    private int subject_id;
    private int t_id;
    private int levelId;
    private int period1Grade;
    private int period2Grade;

    public TeacherGradeDeleteDto(int gradeId, int studentId, int subject_id, int t_id, int levelId, int period1Grade, int period2Grade) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.subject_id = subject_id;
        this.t_id = t_id;
        this.levelId = levelId;
        this.period1Grade = period1Grade;
        this.period2Grade = period2Grade;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public int getT_id() {
        return t_id;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getPeriod1Grade() {
        return period1Grade;
    }

    public int getPeriod2Grade() {
        return period2Grade;
    }
}
