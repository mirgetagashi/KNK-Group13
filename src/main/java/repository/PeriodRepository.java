package repository;

import model.Major;
import model.Period;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeriodRepository {


    public static ArrayList<Period> getAllPeriods(){
        ArrayList<Period> periods = new ArrayList<>();

        try (Connection conn = DBConnector.getConnection()) {
            String query = "SELECT * from Period;";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("period_id");
                String period_name = rs.getString("period_name");
                periods.add(new Period(id,period_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return periods;

    }
    public static Period getPeriodByName(String period) {
        String query = "SELECT * FROM Period WHERE period_name = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, period);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static Period getById(int period_id){
        String query = "SELECT * FROM Period WHERE period_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, period_id);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    private static Period getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("period_id");
            String period_name = result.getString("period_name");
            return new Period(
                    id, period_name
            );
        }catch (Exception e){
            return null;
        }
    }


}
