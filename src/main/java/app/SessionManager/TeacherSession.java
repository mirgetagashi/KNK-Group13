package app.SessionManager;

import model.Teacher;

public class TeacherSession {
    private static Teacher teacher;

    public static void setTeacher(Teacher newTeacher){
        teacher = newTeacher;
    }
    public static Teacher getTeacher(){
        return teacher;
    }
}
