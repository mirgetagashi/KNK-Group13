package model.dto;

public class StudentTeacherDto {
    private int teacherId;
    private int schoolId;
    private int levelId;

    public StudentTeacherDto(int teacherId, int schoolId, int levelId) {
        this.teacherId = teacherId;
        this.schoolId = schoolId;
        this.levelId = levelId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public int getLevelId() {
        return levelId;
    }


}
