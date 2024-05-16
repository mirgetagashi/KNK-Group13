package service;

import model.Students;
import model.dto.CreateGradeDto;
import model.dto.TeacherTableDto;
import repository.GradeRepository;
import repository.StudentRepository;

public class GradeService {

    public static boolean addGrade(TeacherTableDto teacherTableDto){
        Students student = StudentRepository.getById(teacherTableDto.getStudentId());
        if (student == null) {
            System.out.println("Student ID does not exist.");
            return false;
        }
        boolean gradesExist = GradeRepository.gradesExistForStudentAndLevel(teacherTableDto.getStudentId(), teacherTableDto.getLevel());
        if (gradesExist) {
            System.out.println("Grades for this student in this level already exist.");
            return false;
        }





        CreateGradeDto createGradeDto=new CreateGradeDto(
                teacherTableDto.getTeacher_id(),
                teacherTableDto.getSubject_id(),
                teacherTableDto.getStudentId(),
                teacherTableDto.getLevel(),
                teacherTableDto.getPeriod1Grade(),
                teacherTableDto.getPeriod2Grade()
        );

        return GradeRepository.create(createGradeDto);
    }



}
