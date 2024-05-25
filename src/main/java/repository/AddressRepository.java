package repository;

import model.Address;
import Database.DBConnector;
import repository.Interface.AddressInterface;

import java.sql.*;
import java.util.ArrayList;

public class AddressRepository implements AddressInterface {
    public  ArrayList<Address> getAllCities(){
        ArrayList<Address> cities = new ArrayList<>();
        String query = "SELECT * FROM Address";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                Address address= getFromResultSet(result);
                cities.add(address);
            }
        }catch (Exception e){
            return null;
        }
        return cities;
    }



    public  Address getAddressByCity(String city){
        String query = "SELECT * FROM Address WHERE city = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, city);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    public  Address getById(int address_id){
        String query = "SELECT * FROM Address WHERE address_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, address_id);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }



    private static Address getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("address_id");
            String major_name = result.getString("city");
            return new Address(
                    id, major_name
            );
        }catch (Exception e){
            return null;
        }
    }

}
