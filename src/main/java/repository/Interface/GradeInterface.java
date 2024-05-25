package repository.Interface;

import model.Grades;
import model.dto.CreateGradeDto;
import model.dto.DeleteGradeDto;
import model.dto.UpdateGradeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GradeInterface {
    boolean create(CreateGradeDto createGradeDto) throws SQLException;
    boolean deleteGrade(DeleteGradeDto deleteGradeDto) throws SQLException;
    boolean updateGrade(UpdateGradeDto updateGradeDto) throws SQLException;
    List<Integer> getGradesByStudent(int std_id) throws SQLException;
    boolean gradesExistForStudentAndLevel(int studentId, int level_id) throws SQLException;
    boolean isStudentLevelConsistent(int studentId, int levelId) throws SQLException;
    boolean gradesExistForStudentInAnyOtherLevel(int studentId, int currentLevelId) throws SQLException;
    ArrayList<Grades> getAllGrades() throws SQLException;
    int getPeriod1Grade(int gradeId) throws SQLException;
    int getPeriod2Grade(int gradeId) throws SQLException;
    Grades getGradeById(int gradeId) throws SQLException;
    int countGrades() throws SQLException;
    double calculateAverageFinalGrade() throws SQLException;
    double calculateAverageFinalGradeStudent(int std_id) throws SQLException;
}
