package repository;

import model.*;
import model.dto.CreateTeacherDto;
import Database.DBConnector;
import model.filter.StudentFilter;
import model.filter.TeacherFilter;
import repository.Interface.TeacherInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TeacherRepository implements TeacherInterface {
    public  boolean create(CreateTeacherDto userData){
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


    public  boolean delete(int id) {

        String query = "DELETE FROM Teachers WHERE t_id = ?";

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


    public  Teacher getByEmail(String email){
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

    public  Teacher getById(int std_id){
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

    public  ArrayList<Teacher> getAllTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM Teachers;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                Teacher teacher= getFromResultSet(result);
                teachers.add(teacher);
            }
        }catch (Exception e){
            return null;
        }
        return teachers;
    }

    public  ArrayList<Teacher> getByFilter(TeacherFilter filter) throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Teachers WHERE 1=1 ");
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
                Teacher teacher = getFromResultSet(result);
                teachers.add(teacher);
            }
        } catch (Exception e) {
            return null;
        }
        return teachers;
    }

    private  Teacher getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("t_id");
            String firstName = result.getString("t_name");
            String lastName = result.getString("t_lastName");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            int address = result.getInt("address_id");
            String title=result.getString("title");
            String education= result.getString("education");
            int school_id=result.getInt("school_id");
            int subject_id=result.getInt("subject_id");
            String gender=result.getString("gender");
            Date birthday=result.getDate("birthday");



            return new Teacher(
                    id, firstName, lastName, email, salt, passwordHash, address,gender, (java.sql.Date) birthday,title,education,school_id,subject_id
            );
        }catch (Exception e){
            return null;
        }
    }




}
