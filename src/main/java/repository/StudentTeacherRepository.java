package repository;

import Database.DBConnector;
import model.dto.StudentTeacherDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentTeacherRepository {
    public static boolean addStudentTeacher(int teacher_id, int school_id, int level_id){
        Connection conn = DBConnector.getConnection();

        String query="CALL AddStudentsToTeacher(?, ?,  ?)";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, teacher_id);
            pst.setInt(2, school_id);
            pst.setInt(3, level_id);

            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static int getNumberOfStudentsByTeacherId(int teacher_id) {
        String query = "SELECT COUNT(*) AS number_of_students FROM student_teacher WHERE t_id = ?;";
        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, teacher_id);
            ResultSet resultSet = pst.executeQuery();
            int numberOfStudents = 0;
            if (resultSet.next()) {
                numberOfStudents = resultSet.getInt("number_of_students");
            }
            resultSet.close();
            pst.close();
            conn.close();
            return numberOfStudents;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static ArrayList<Integer> getTeacherStudents(int school_id, int level_id){
        String query = "select * from students where school_id=? and level_id=?;";
        ArrayList<Integer> studentsId= new ArrayList<>();
        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1,school_id);
            pst.setInt(1,level_id);
            try(ResultSet resultSet = pst.executeQuery()){
                if (resultSet.next()){
                    studentsId.add(resultSet.getInt("std_id"));
                }
            }
            pst.close();
            conn.close();
            return studentsId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
