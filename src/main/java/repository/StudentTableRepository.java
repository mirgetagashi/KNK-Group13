package repository;
import model.Grades_Teacher_Subject;
import model.Students;
import service.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class StudentTableRepository {
    public static ArrayList<Grades_Teacher_Subject> getStudentsTable(int id) {
        ArrayList<Grades_Teacher_Subject> gts = new ArrayList<>();
        Connection connection = DBConnector.getConnection();

        String query = "SELECT " +
                "s.subject_id AS subject_id, " +
                "s.subject_name AS subject_name, " +
                "t.t_name AS teacher_firstName, " +
                "t.t_lastName AS teacher_lastName, " +
                "g.period1_grade AS first_period_grade, " +
                "g.period2_grade AS second_period_grade, " +
                "g.final_grade AS final_grade " +
                "FROM " +
                "Grades g " +
                "JOIN " +
                "Subjects s ON g.subject_id = s.subject_id " +
                "JOIN " +
                "Teachers t ON g.t_id = t.t_id " +
                "WHERE std_id = ? ;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grades_Teacher_Subject data= getFromResultSet(resultSet);
                gts.add(data);

            }

        } catch (SQLException e) {
            return null;
        }
        return gts;
    }

    private static Grades_Teacher_Subject getFromResultSet(ResultSet result){
        try{
            int subject_id=result.getInt("subject_id");
            String  subject_name=result.getString("subject_name");
            String teacher_first_name = result.getString("teacher_firstName");
            String teacher_last_name = result.getString("teacher_lastName");
            int first_period_grade = result.getInt("first_period_grade");
            int second_period_grade = result.getInt("second_period_grade");
            int final_grade = result.getInt("final_grade");
            return new Grades_Teacher_Subject(
                    teacher_first_name, teacher_last_name, subject_id, subject_name, first_period_grade, second_period_grade, final_grade
            );
        }catch (Exception e){
            return null;
        }
    }
}

