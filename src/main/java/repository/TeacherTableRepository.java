package repository;

import model.Grades;
import Database.DBConnector;
import model.filter.TeacherTableFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherTableRepository {

public static ArrayList<Grades> getGradesTable(){
    ArrayList<Grades>grades=new ArrayList<>();
    Connection conn= DBConnector.getConnection();

    String query="SELECT * FROM Grades ";
    try {
        PreparedStatement statement= conn.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            Grades data=getFromResultSet(resultSet);
            grades.add(data);
        }
    }
    catch (SQLException e){
        return null;
    }
    return grades;

}
    public static ArrayList<Grades> getGradesByFilter(TeacherTableFilter filter) {
        ArrayList<Grades> grades = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Grades WHERE 1=1 ");
        query.append(filter.buildQuery());

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query.toString())) {
            ArrayList<Object> params = filter.getFilterParams();
            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                grades.add(new Grades(
                        result.getInt("grade_id"),
                        result.getInt("level_id"),
                        result.getInt("period1_grade"),
                        result.getInt("period2_grade"),
                        result.getInt("final_grade"),
                        result.getInt("std_id"),
                        result.getInt("subject_id"),
                        result.getInt("t_id")
                ));
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
