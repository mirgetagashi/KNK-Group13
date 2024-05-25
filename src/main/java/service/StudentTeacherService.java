package service;

import model.dto.StudentTeacherDto;
import repository.StudentTeacherRepository;

import java.util.ArrayList;

public class StudentTeacherService {

    public static boolean addStudentTeacher(StudentTeacherDto userData){
        int teacher_id= userData.getTeacherId();
        int school_id=userData.getSchoolId();
        int level_id= userData.getLevelId();

        ArrayList<Integer> studentsId= new ArrayList<>();

        return StudentTeacherRepository.addStudentTeacher(teacher_id,school_id,level_id);
    }
}
