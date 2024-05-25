package repository;

import model.*;
import model.dto.ChangePasswordDto;
import model.dto.CreateStudentDto;
import Database.DBConnector;
import model.dto.UpdateGradeDto;
import model.dto.UpdateStudentDto;
import model.filter.StudentFilter;
import repository.Interface.StudentInterface;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepository implements StudentInterface {


    public  boolean create(CreateStudentDto userData){
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


    public  Students getByEmail(String email){
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

    public  Students getById(int std_id){
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


    public  ArrayList<Students> getAllStudents(){
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

    public  boolean delete(int id) {

        String query = "DELETE FROM Students WHERE std_id = ?";

        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst= conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  boolean update(UpdateStudentDto userData) {
        String query = "UPDATE Students SET std_name = ?, std_lastName = ?, address_id = ?, school_id = ?, major_id = ?, level_id = ?  WHERE std_id = ?";

        try {
            Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setInt(3, userData.getAddress());
            pst.setInt(4, userData.getSchool());
            pst.setInt(5, userData.getMajor());
            pst.setInt(6, userData.getLevel());
            pst.setInt(7,userData.getId());

            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public  String getSaltById(int studentId){
        String query = "SELECT salt FROM Students WHERE id = ?";
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement pst = connection.prepareStatement(query)){
            pst.setInt(1, studentId);
            try(ResultSet resultSet = pst.executeQuery()){
                if (resultSet.next()){
                    return resultSet.getString("salt");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  boolean doesStudentExist(String email) {
        String query = "SELECT COUNT(*) > 0 AS student_exists FROM Students WHERE email = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:your_database_url", "your_db_user", "your_db_password");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("student_exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public  boolean updatePassword(ChangePasswordDto userData) {
        String newHashPassword=userData.getNewHashPassword();
        int id= userData.getId();
        String query = "UPDATE Students SET passwordHash = ? WHERE std_id = ?;";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, newHashPassword);
            pst.setInt(2, id);

            pst.execute();
            pst.close();
            connection.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public  ArrayList<Students> getByFilter(StudentFilter filter) throws SQLException {
        ArrayList<Students> students = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Students WHERE 1=1 ");
        ArrayList<Object> params = new ArrayList<>();

        query.append(filter.buildQuery());
        params.addAll(filter.getFilterParams());

        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement pst = connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Students student = getFromResultSet(result);
                students.add(student);
            }
        } catch (Exception e) {
            return null;
        }
        return students;
    }





    private  Students getFromResultSet(ResultSet result){
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