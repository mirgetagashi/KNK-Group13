package repository;

import model.Address;
import model.Grade_level;
import model.Major;
import model.Period;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeLevelRepository {

    public static Grade_level getLevelByName(String level) {
        String query = "SELECT * FROM Grade_level WHERE level_name = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, level);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }



    public static ArrayList<Grade_level> getAllLevels() {
        ArrayList<Grade_level> levels = new ArrayList<>();

        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT * from Grade_level;";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("level_id");
                String level_name = rs.getString("level_name");
                levels.add(new Grade_level(id, level_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;

    }
    public static Grade_level getLevelById(int id) {
        String query = "SELECT * FROM Grade_level WHERE level_id = ? LIMIT 1;";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return getFromResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Grade_level getFromResultSet(ResultSet result) {
        try {
            int id = result.getInt("level_id");
            String level_name = result.getString("level_name");
            return new Grade_level(
                    id, level_name
            );
        } catch (Exception e) {
            return null;
        }
    }


}