package app.SessionManager;

import model.Students;
import model.Teacher;

public class StudentSession {

    private static Students student;

    public static void setStudent(Students newStudent){
        student = newStudent;
    }
    public static Students getStudent(){
        return student;
    }
}
