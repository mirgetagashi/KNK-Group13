package model.dto;

public class CreateGradeDto {
    private Integer t_id;
    private Integer subject_id;
    private Integer studentId;
    private int level_id;
    private Integer period1Grade;
    private Integer period2Grade;

    public CreateGradeDto(Integer t_id, Integer subject_id, Integer studentId, int level_id, Integer period1Grade, Integer period2Grade) {
        this.t_id = t_id;
        this.subject_id = subject_id;
        this.studentId = studentId;
        this.level_id = level_id;
        this.period1Grade = period1Grade;
        this.period2Grade = period2Grade;
    }

    public Integer getT_id() {
        return t_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public int getLevel_Id() {
        return level_id;
    }

    public Integer getPeriod1Grade() {
        return period1Grade;
    }

    public Integer getPeriod2Grade() {
        return period2Grade;
    }
}