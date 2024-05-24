package service;

import model.Grades;
import model.Students;
import model.dto.*;
import repository.GradeRepository;
import repository.StudentRepository;
import repository.TeacherTableRepository;

import java.util.ArrayList;

public class GradeService {

    public static boolean addGrade(TeacherTableDto teacherTableDto) {
        Students student = StudentRepository.getById(teacherTableDto.getStudentId());
        if (student == null) {
            System.out.println("Student ID does not exist.");
            return false;
        }

        boolean gradesExist = GradeRepository.gradesExistForStudentAndLevel(teacherTableDto.getStudentId(), teacherTableDto.getLevel_Id());
        if (gradesExist) {
            System.out.println("Grades for this student in this level already exist.");
            return false;
        }


        int currentLevelId = teacherTableDto.getLevel_Id();
        boolean gradesInOtherLevels = GradeRepository.gradesExistForStudentInAnyOtherLevel(teacherTableDto.getStudentId(), currentLevelId);
        if (gradesInOtherLevels) {
            System.out.println("Student already has grades in another level.");
            return false;
        }
        boolean isLevelConsistent = GradeRepository.isStudentLevelConsistent(teacherTableDto.getStudentId(), teacherTableDto.getLevel_Id());
        if (!isLevelConsistent) {
            System.out.println("The student's level does not match the level being assigned.");
            return false;
        }
        CreateGradeDto createGradeDto = new CreateGradeDto(
                teacherTableDto.getTeacher_id(),
                teacherTableDto.getSubject_id(),
                teacherTableDto.getStudentId(),
                teacherTableDto.getLevel_Id(),
                teacherTableDto.getPeriod1Grade(),
                teacherTableDto.getPeriod2Grade()
        );

        return GradeRepository.create(createGradeDto);
    }

    public static boolean deleteGrade(TeacherGradeDeleteDto teacherGradeDeleteDto) {


        DeleteGradeDto deleteGradeDto = new DeleteGradeDto(
                teacherGradeDeleteDto.getGradeId(),
                teacherGradeDeleteDto.getStudentId(),
                teacherGradeDeleteDto.getSubject_id(),
                teacherGradeDeleteDto.getT_id(),
                teacherGradeDeleteDto.getLevelId(),
                teacherGradeDeleteDto.getPeriod1Grade(),
                teacherGradeDeleteDto.getPeriod2Grade()


        );
        return GradeRepository.deleteGrade(deleteGradeDto);
    }
    public static ArrayList<Grades> getAllGrades(){
        return GradeRepository.getAllGrades();
    }
    public static ArrayList<Grades> getGradesTable(){return TeacherTableRepository.getGradesTable();}

    public static boolean updateGrade(TeacherGradeUpdateDto teacherGradeUpdateDto) {
        int gradeId = teacherGradeUpdateDto.getGradeId();
        int studentId= teacherGradeUpdateDto.getStudentId();
        int subjectId=teacherGradeUpdateDto.getSubject_id();
        int tId = teacherGradeUpdateDto.getT_id();
        int levelId = teacherGradeUpdateDto.getLevelId();
        int newPeriod1Grade = teacherGradeUpdateDto.getPeriod1Grade();
        int newPeriod2Grade = teacherGradeUpdateDto.getPeriod2Grade();


        boolean gradesChanged = newPeriod1Grade != GradeRepository.getPeriod1Grade(gradeId)
                || newPeriod2Grade != GradeRepository.getPeriod2Grade(gradeId);

        if (gradesChanged) {
            UpdateGradeDto updateGradeDto = new UpdateGradeDto(
                    gradeId,
                    studentId,
                    subjectId,
                    tId,
                    levelId,
                    newPeriod1Grade,
                    newPeriod2Grade


            );


            return GradeRepository.updateGrade(updateGradeDto);
        }


        return false;
    }
    public static double calculateAverageFinalGradeStudent(int std_id) {return GradeRepository.calculateAverageFinalGradeStudent(std_id);}




}




