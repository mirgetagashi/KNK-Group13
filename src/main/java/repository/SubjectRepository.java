package repository;

import model.Subject;
import Database.DBConnector;
import repository.Interface.SubjectInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectRepository implements SubjectInterface {

    public  ArrayList<Subject> getAllSubjects(){
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

    public  Subject getSubjectByName(String subject){
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

    public  Subject getById(int subject_id){
        String query = "SELECT * FROM Subjects WHERE subject_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, subject_id);
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


            return new Subject(
                    id, name
            );
        }catch (Exception e){
            return null;
        }
    }}
