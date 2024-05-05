package repository;

import model.*;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {

    public static Administrator getByEmail(String email){
        String query = "SELECT * FROM Administrator WHERE email = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static Administrator getById(int std_id){
        String query = "SELECT * FROM Administrator WHERE std_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, std_id);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    private static Administrator getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("a_id");
            String firstName = result.getString("a_name");
            String lastName = result.getString("a_lastName");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            Address address = AddressRepository.getById(result.getInt("address_id"));


            return new Administrator(
                    id, firstName, lastName, email, salt, passwordHash, address
            );
        }catch (Exception e){
            return null;
        }
    }
}
