package repository;

import model.*;
import model.dto.CreateStudentDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherRepository {
    public static boolean create(CreateStudentDto userData){
        Connection conn = DBConnector.getConnection();
        String query = " INSERT INTO Teachers (t_name, t_lastName, email, username, salt, passwordHash, address_id, school_id, major_id, period_id) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getEmail());
            pst.setString(5, userData.getSalt());
            pst.setString(6, userData.getPasswordHash());
            pst.setInt(7,userData.getAddress().getId());
            pst.setInt(8,userData.getSchool().getId());
            pst.setInt(9,userData.getMajor().getId());
            pst.setInt(10,userData.getPeriod().getId());



            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public static Teacher getByEmail(String email){
        String query = "SELECT * FROM Teachers WHERE email = ? LIMIT 1;";
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

    public static Teacher getById(int std_id){
        String query = "SELECT * FROM Teachers WHERE t_id = ? LIMIT 1;";
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

    private static Teacher getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("id");
            String firstName = result.getString("t_name");
            String lastName = result.getString("t_lastName");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            Address address = AddressRepository.getById(result.getInt("address_id"));


            return new Teacher(
                    id, firstName, lastName, email, salt, passwordHash, address
            );
        }catch (Exception e){
            return null;
        }
    }




}
