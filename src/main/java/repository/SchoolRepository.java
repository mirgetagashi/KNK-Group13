package repository;

import model.*;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import Database.DBConnector;
import model.filter.SchoolFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchoolRepository {



    public static boolean create(CreateSchoolDto userData){
        Connection conn = DBConnector.getConnection();
        String query = " INSERT INTO SCHOOL (school_name, address_id) VALUES (?, ?); ";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getName());
            pst.setInt(2, userData.getAddress_id());

            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public static ArrayList<SchoolTable> getByFilter(SchoolFilter filter) throws SQLException {
        ArrayList<SchoolTable> schools = new ArrayList<>();
        StringBuilder query = new StringBuilder("select * from School_Table_Info where 1=1 ");
        ArrayList<Object> params = new ArrayList<>();

        String nameQueryPart = "";
        String cityQueryPart = "";

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            nameQueryPart = "school_name like ?";
            params.add(filter.getName() + "%");
        }

        if (filter.getCity() != null && !filter.getCity().isEmpty()) {
            cityQueryPart = "city like ?";
            params.add(filter.getCity() + "%");
        }

        if (!nameQueryPart.isEmpty() && !cityQueryPart.isEmpty()) {
            query.append(" AND (" + nameQueryPart + " OR " + cityQueryPart + ")");
        } else if (!nameQueryPart.isEmpty()) {
            query.append(" AND " + nameQueryPart);
        } else if (!cityQueryPart.isEmpty()) {
            query.append(" AND " + cityQueryPart);
        }

        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement pst = connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                schools.add(new SchoolTable(
                        result.getInt("School_id"),
                        result.getString("School_Name"),
                        result.getString("City"),
                        result.getInt("Number_of_Students"),
                        result.getInt("Number_of_Majors")
                ));
            }
        } catch (Exception e) {
            return null;
        }
        return schools;
    }


    public static boolean delete(int id){
        String query = "DELETE FROM School WHERE school_id = ?";

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


   public static ArrayList<School> getSchoolByCity(int city_id) {
       ArrayList<School> schools = new ArrayList<>();

       String query = "select * from school where address_id=?";
       Connection conn = DBConnector.getConnection();
       try{
           PreparedStatement pst = conn.prepareStatement(query);
           pst.setInt(1, city_id);
           ResultSet rs = pst.executeQuery();
           while (rs.next()) {
               int school_id=rs.getInt("school_id");
               String school_name = rs.getString("school_name");
               int address_id=rs.getInt("address_id");
               schools.add(new School(school_id, school_name, address_id));
           }
       } catch (SQLException e) {
           System.out.println("Diçka ka shkuar gabim: " + e.getMessage());
       }
       return schools;
   }

    public static ArrayList<School> getAllSchools(){
        ArrayList<School> schools = new ArrayList<>();
        String query = "SELECT * FROM School;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                School school= getFromResultSet(result);
                schools.add(school);
            }
        }catch (Exception e){
            return null;
        }
        return schools;
    }

    public static boolean addSchoolMajor(AddSchoolMajorDto userData){
        String query = "insert into school_major(school_id, major_id) values(?, ?);";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, userData.getSchool_id());
            pst.setInt(2, userData.getMajor_id());
            pst.execute();
            pst.close();
            connection.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static School_Major getSchoolMajor(AddSchoolMajorDto userData){
        int school_id= userData.getSchool_id();
        int major_id= userData.getMajor_id();
        String query="select * from school_major where school_id=? and major_id=? LIMIT 1;";
        Connection connection=DBConnector.getConnection();
        try{
            PreparedStatement pst= connection.prepareStatement(query);
            pst.setInt(1, school_id);
            pst.setInt(2, major_id);
            ResultSet result=pst.executeQuery();
            if(result.next()){

                int sID = result.getInt("school_id");
                int mID=result.getInt("major_id");
                return new School_Major(sID,mID);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }



    public static School getById(int school_id){
        String query = "SELECT * FROM School WHERE school_id = ? LIMIT 1;";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, school_id);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    private static School getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("school_id");
            String name = result.getString("school_name");
            int address_id=result.getInt("address_id");
            return new School(
                    id, name, address_id
            );
        }catch (Exception e){
            return null;
        }
    }



}
