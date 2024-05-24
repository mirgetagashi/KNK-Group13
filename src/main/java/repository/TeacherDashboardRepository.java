package repository;

import Database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDashboardRepository {
    public static double calculateAverageFinalGradeByTeacherId(int teacherId) {
        String query = "SELECT AVG(final_grade) FROM Grades WHERE t_id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, teacherId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public static List<Integer> getGradesByTeacherId(int teacherId) {
        List<Integer> teacherGrades = new ArrayList<>();
        String query = "SELECT final_grade FROM Grades WHERE t_id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, teacherId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                teacherGrades.add(resultSet.getInt("final_grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherGrades;
    }
}
