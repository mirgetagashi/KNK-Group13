package repository;

import model.Major;
import Database.DBConnector;
import repository.Interface.MajorInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MajorRepository implements MajorInterface {

    public  ArrayList<Major> getMajorBySchool(int school_id){
        ArrayList<Major> majors = new ArrayList<>();

        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT m.major_name, m.major_id FROM majors m " +
                    "INNER JOIN school_major sm ON sm.major_id = m.major_id " +
                    "WHERE sm.school_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, school_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int major_id=rs.getInt("major_id");
                String major_name = rs.getString("major_name");
                majors.add(new Major(major_id,major_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return majors;
    }

    public  Major getMajorByName(String major_name){
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

    public  Major getById(int major_id){
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

    public  ArrayList<Major> getAllMajors(){
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

    private  Major getFromResultSet(ResultSet result){
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
