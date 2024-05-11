package repository;

import model.*;
import model.dto.CreateStudentDto;
import model.dto.CreateTeacherDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherRepository {
    public static boolean create(CreateTeacherDto userData){
        Connection conn = DBConnector.getConnection();
        String query = " INSERT INTO TEACHERS (t_name, t_lastName, email, salt, passwordHash, address_id, education, school_id, gender,title, subject_id, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getEmail());
            pst.setString(4, userData.getSalt());
            pst.setString(5, userData.getPasswordHash());
            pst.setInt(6,userData.getAddress().getId());
            pst.setString(7,userData.getEducation());
            pst.setInt(8,userData.getSchool().getId());
            pst.setString(9,userData.getGender());
            pst.setString(10,userData.getTitle());
            pst.setInt(11,userData.getSubject().getId());
            pst.setDate(12,userData.getBirthday());



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
