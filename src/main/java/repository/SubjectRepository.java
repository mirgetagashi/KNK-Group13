package repository;

import model.School;
import model.Subject;
import model.dto.CreateStudentDto;
import model.dto.CreateTeacherDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectRepository {



    public static ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM Subjects;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                Subject subject= getFromResultSet(result);
                subjects.add(subject);
            }
        }catch (Exception e){
            return null;
        }
        return subjects;
    }

    public static Subject getSubjectByName(String subject){
        String query = "SELECT * FROM SUBJECTS WHERE subject_name = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, subject);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    private static Subject getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("subject_id");
           String name=result.getString("subject_name");
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

            return new Subject(
                    id, name
            );
        }catch (Exception e){
            return null;
        }
    }}
