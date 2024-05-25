package service;

import model.dto.StudentTeacherDto;
import repository.GradeRepository;
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
    public static double calculateAverageFinalGradeStudent(int std_id) {return GradeRepository.calculateAverageFinalGradeStudent(std_id);}

    public static double getNumberOfStudentsByTeacherId(int teacher_id){return StudentTeacherRepository.getNumberOfStudentsByTeacherId(teacher_id);}
}
