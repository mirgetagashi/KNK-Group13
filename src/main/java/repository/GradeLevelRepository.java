package repository;

import model.Grade_level;
import model.Period;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeLevelRepository {

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