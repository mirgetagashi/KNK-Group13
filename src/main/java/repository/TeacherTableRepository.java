package repository;

import model.Grades;
import service.DBConnector;

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
