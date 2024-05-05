package repository;

import model.Address;
import model.Major;
import model.School;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MajorRepository {

    public static ArrayList<String> getMajorBySchool(String school){
        ArrayList<String> majors = new ArrayList<>();

        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT m.major_name FROM majors m " +
                    "INNER JOIN school_major sm ON sm.major_id = m.major_id " +
                    "INNER JOIN School s ON s.school_id = sm.school_id " +
                    "WHERE s.school_name = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, school);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String major_name = rs.getString("major_name");
                majors.add(major_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majors;
    }

    public static Major getMajorByName(String major_name){
        String query = "SELECT * FROM MAJORS WHERE major_name = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, major_name);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static Major getById(int major_id){
        String query = "SELECT * FROM Majors WHERE major_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, major_id);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static ArrayList<Major> getAllMajors(){
        ArrayList<Major> majors = new ArrayList<>();
        String query = "SELECT * FROM MAJORS";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                majors.add(getFromResultSet(result));
            }
        }catch (Exception e){
            return null;
        }
        return majors;
    }

    private static Major getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("major_id");
            String major_name = result.getString("major_name");
            return new Major(
                    id, major_name
            );
        }catch (Exception e){
            return null;
        }
    }

}
