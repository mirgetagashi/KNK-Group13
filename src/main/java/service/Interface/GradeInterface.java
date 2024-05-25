package service.Interface;

import model.Grades;
import model.Students;
import model.dto.*;
import model.filter.TeacherTableFilter;
import repository.GradeRepository;
import repository.StudentRepository;
import repository.TeacherTableRepository;

import java.util.ArrayList;

public interface GradeInterface {
    boolean addGrade(TeacherTableDto teacherTableDto) ;

    ArrayList<Grades> filterGrades(TeacherTableFilter filteri);

    boolean deleteGrade(TeacherGradeDeleteDto teacherGradeDeleteDto);
    ArrayList<Grades> getAllGrades();
    ArrayList<Grades> getGradesTable();

    boolean updateGrade(TeacherGradeUpdateDto teacherGradeUpdateDto);
    double calculateAverageFinalGradeStudent(int std_id);

}
