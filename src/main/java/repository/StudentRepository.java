package repository;

import model.*;
import model.dto.CreateStudentDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentRepository {
    public static boolean create(CreateStudentDto userData){
        Connection conn = DBConnector.getConnection();
        String query = " INSERT INTO STUDENTS (std_name, std_lastName, email, salt, passwordHash, address_id, school_id, major_id, period_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getEmail());
            pst.setString(4, userData.getSalt());
            pst.setString(5, userData.getPasswordHash());
            pst.setInt(6,userData.getAddress().getId());
            pst.setInt(7,userData.getSchool().getId());
            pst.setInt(8,userData.getMajor().getId());
            pst.setInt(9,userData.getPeriod().getId());



            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public static Students getByEmail(String email){
        String query = "SELECT * FROM STUDENTS WHERE email = ? LIMIT 1;";
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

    public static Students getById(int std_id){
        String query = "SELECT * FROM Students WHERE std_id = ? LIMIT 1;";
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

    private static Students getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("id");
            String firstName = result.getString("std_name");
            String lastName = result.getString("std_lastName");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            Address address = AddressRepository.getById(result.getInt("address_id"));
            School school = SchoolRepository.getById(result.getInt("school_id"));
            Major major =MajorRepository.getById(result.getInt("major_id")) ;
            Period period = PeriodRepository.getById(result.getInt("period_id"));


            return new Students(
                    id, firstName, lastName, email, salt, passwordHash, address,school,major,period
            );
        }catch (Exception e){
            return null;
        }
    }




}
