package model.dto;

public class DeleteGradeDto {
    private int grade_id;
    private int t_id;
    private int subject_id;
    private int studentId;
    private int level_id;
    private int period1Grade;
    private int period2Grade;

    public DeleteGradeDto(int grade_id, int t_id, int subject_id, int studentId, int level_id, int period1Grade, int period2Grade) {
        this.grade_id = grade_id;
        this.t_id = t_id;
        this.subject_id = subject_id;
        this.studentId = studentId;
        this.level_id = level_id;
        this.period1Grade = period1Grade;
        this.period2Grade = period2Grade;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public int getT_id() {
        return t_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getLevel_id() {
        return level_id;
    }

    public int getPeriod1Grade() {
        return period1Grade;
    }

    public int getPeriod2Grade() {
        return period2Grade;
    }
}

