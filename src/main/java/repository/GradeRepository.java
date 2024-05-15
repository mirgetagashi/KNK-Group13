package repository;

import model.Grades;
import model.dto.CreateGradeDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeRepository {

    public static boolean create(CreateGradeDto createGradeDto) {
        Connection conn = DBConnector.getConnection();
        String query = "INSERT INTO GRADES (level_id, period1_grade, period2_grade, final_grade, std_id, subject_id, t_id) " +
                "VALUES (?, ?, ?, calculate_final_grade(?, ?), ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, GradeLevelRepository.getLevelByName(createGradeDto.getLevel()).getLevel_id());
            pst.setInt(2, createGradeDto.getPeriod1Grade());
            pst.setInt(3, createGradeDto.getPeriod2Grade());
            pst.setInt(4, createGradeDto.getPeriod1Grade());
            pst.setInt(5, createGradeDto.getPeriod2Grade());
            pst.setInt(6, createGradeDto.getStudentId());
            pst.setInt(7, createGradeDto.getSubject_id());
            pst.setInt(8, createGradeDto.getT_id());

            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteGrade(int gradeId) {

        String query = "DELETE FROM Grades WHERE grade_id = ?";

        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst= conn.prepareStatement(query);
            pst.setInt(1, gradeId);
            int deletedRow = pst.executeUpdate();
            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    public static boolean gradesExistForStudentAndLevel(int studentId, String level) {
        String query = "SELECT COUNT(*) FROM grades WHERE std_id = ? AND level_id = ? AND (period1_grade IS NOT NULL OR period2_grade IS NOT NULL)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, GradeLevelRepository.getLevelByName(level).getLevel_id());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Grades> getAllGrades() {
        ArrayList<Grades> grades = new ArrayList<>();
        String query = "SELECT * FROM Grades";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet resultSet = pst.executeQuery()) {
            while (resultSet.next()) {
                Grades grade = getFromResultSet(resultSet);
                if (grade != null) {
                    grades.add(grade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return grades;
    }


    private static Grades getFromResultSet(ResultSet resultSet) {
        try {
            int gradeId = resultSet.getInt("grade_id");
            int levelId = resultSet.getInt("level_id");
            int period1Grade = resultSet.getInt("period1_grade");
            int period2Grade = resultSet.getInt("period2_grade");
            int finalGrade = resultSet.getInt("final_grade");
            int studentId = resultSet.getInt("std_id");
            int subjectId = resultSet.getInt("subject_id");
            int teacherId = resultSet.getInt("t_id");

            return new Grades(gradeId, levelId, period1Grade, period2Grade, finalGrade, studentId, subjectId, teacherId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
