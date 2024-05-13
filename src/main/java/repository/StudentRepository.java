package repository;

import model.*;
import model.dto.CreateStudentDto;
import model.dto.StudentDto;
import repository.Interfaces.StudentInterface;
import service.DBConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentRepository {


    public static boolean create(CreateStudentDto userData){
        Connection conn = DBConnector.getConnection();
        String query = " INSERT INTO STUDENTS (std_name, std_lastName, email, salt, passwordHash, address_id, school_id, major_id, level_id,gender, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getEmail());
            pst.setString(4, userData.getSalt());
            pst.setString(5, userData.getPasswordHash());
            pst.setInt(6,userData.getAddress());
            pst.setInt(7,userData.getSchool());
            pst.setInt(8,userData.getMajor());
            pst.setInt(9,userData.getPeriod());
            pst.setString(10,userData.getGender());
            pst.setDate(11,userData.getBirthday());



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


    public static ArrayList<Students> getAllStudents(){
        ArrayList<Students> students = new ArrayList<>();
        String query = "SELECT * FROM Students;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                Students student= getFromResultSet(result);
                students.add(student);
            }
        }catch (Exception e){
            return null;
        }
        return students;
    }


    private static Students getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("std_id");
            String firstName = result.getString("std_name");
            String lastName = result.getString("std_lastName");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            int address = result.getInt("address_id");
            int school = result.getInt("school_id");
            int major =result.getInt("major_id") ;
            int level = result.getInt("level_id");
            String gender=result.getString("gender");
            Date birthday=result.getDate("birthday");



            return new Students(
                    id, firstName, lastName, email, salt, passwordHash, address,school,major,level, gender, birthday
            );
        }catch (Exception e){
            return null;
        }
    }



}
