package model.dto;

public class TeacherTableDto {
    private int teacher_id;
    private int subject_id;
    private Integer studentId;
    private String level;
    private Integer period1Grade;
    private Integer period2Grade;

    public TeacherTableDto(int teacher_id, int subject_id, Integer studentId, String level, Integer period1Grade, Integer period2Grade) {
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.studentId = studentId;
        this.level = level;
        this.period1Grade = period1Grade;
        this.period2Grade = period2Grade;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getLevel() {
        return level;
    }

    public Integer getPeriod1Grade() {
        return period1Grade;
    }

    public Integer getPeriod2Grade() {
        return period2Grade;
    }
}



