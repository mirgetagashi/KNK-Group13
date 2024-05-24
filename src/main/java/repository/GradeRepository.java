package repository;

import model.Grades;
import model.dto.CreateGradeDto;
import model.dto.DeleteGradeDto;
import model.dto.UpdateGradeDto;
import Database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeRepository {

    public static boolean create(CreateGradeDto createGradeDto) {
        Connection conn = DBConnector.getConnection();
        String query = "INSERT INTO GRADES (level_id, period1_grade, period2_grade, final_grade, std_id, subject_id, t_id) " +
                "VALUES (?, ?, ?, calculate_final_grade(?, ?), ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, createGradeDto.getLevel_Id());
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
    public static boolean deleteGrade(DeleteGradeDto deleteGradeDto) {

        String query = "DELETE FROM Grades WHERE grade_id = ?";
        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, deleteGradeDto.getGrade_id());
            int deletedRow = pst.executeUpdate();
            pst.close();
            conn.close();
            return deletedRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateGrade(UpdateGradeDto updateGradeDto) {
        String query = "UPDATE Grades SET period1_grade = ?, period2_grade = ?, final_grade = calculate_final_grade(?, ?) " +
                "WHERE grade_id = ?";
        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, updateGradeDto.getPeriod1_grade());
            pst.setInt(2, updateGradeDto.getPeriod2_grade());
            pst.setInt(3, updateGradeDto.getPeriod1_grade());
            pst.setInt(4, updateGradeDto.getPeriod2_grade());
            pst.setInt(5, updateGradeDto.getGrade_id());

            int updatedRows = pst.executeUpdate();
            pst.close();
            conn.close();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Map<String, Integer> getGradesByStudent(String username) {
        Map<String, Integer> grades = new HashMap<>();
        String query = "SELECT g.subject, g.grade FROM grades g INNER JOIN students s ON g.student_id = s.id WHERE s.username = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                grades.put(rs.getString("subject"), rs.getInt("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }




    public static boolean gradesExistForStudentAndLevel(int studentId, int level_id) {
        String query = "SELECT COUNT(*) FROM grades WHERE std_id = ? AND level_id = ? AND (period1_grade IS NOT NULL OR period2_grade IS NOT NULL)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, level_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean gradesExistForStudentInAnyOtherLevel(int studentId, int currentLevelId) {
        String query = "SELECT COUNT(*) FROM grades WHERE std_id = ? AND level_id <> ? AND (period1_grade IS NOT NULL OR period2_grade IS NOT NULL)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, currentLevelId);
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

    public static int getPeriod1Grade(int gradeId) {
        String query = "SELECT period1_grade FROM Grades WHERE grade_id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, gradeId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("period1_grade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return a sentinel value or handle error appropriately
    }
    public static int getPeriod2Grade(int gradeId) {
        String query = "SELECT period2_grade FROM Grades WHERE grade_id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, gradeId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("period2_grade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return a sentinel value or handle error appropriately
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

    public static Grades getGradeById(int gradeId) {
        String query = "SELECT * FROM Grades WHERE grade_id = ? LIMIT 1;";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, gradeId);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int countGrades() {
        String query = "SELECT COUNT(*) FROM Grades";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1); // Kthen numrin total të rreshtave
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Kthehet 0 nëse ka ndonjë problem
    }
    public static double calculateAverageFinalGrade() {
        String query = "SELECT AVG(final_grade) FROM Grades";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1); // Kthen mesataren e notave finale
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Kthehet 0.0 nëse ka ndonjë problem
    }


}

