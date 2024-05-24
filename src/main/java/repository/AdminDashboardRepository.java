package repository;

import model.SchoolTable;
import model.School_Statistic;
import Database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDashboardRepository {
    public static ArrayList<School_Statistic> Schools_Statistic(){
        ArrayList<School_Statistic> schools= new ArrayList<>();
        String query="select * from Schools_Statistic";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while(result.next()){
                schools.add(new School_Statistic(
                        result.getString("School_Name"),
                        result.getDouble("Average_Grade")
                ));
            }
        }catch (Exception e){
            System.out.println("Ka problem tek databaza");
        }
        return schools;

    }



    public static int Schools_Number(){
        String query="SELECT COUNT(*) AS Number_of_Schools FROM School;";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getInt("Number_of_Schools");
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int Students_Number(){
        String query="SELECT COUNT(*) AS Number_of_Students FROM Students;";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getInt("Number_of_Students");
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int Teacher_Number(){
        String query="SELECT COUNT(*) AS Number_of_Teachers FROM Teachers;";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getInt("Number_of_Teachers");
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<SchoolTable> getSchoolsInfo(){
        ArrayList<SchoolTable> schools= new ArrayList<>();
        String query="select * from School_Table_Info";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while(result.next()){
                schools.add(new SchoolTable(
                        result.getInt("School_id"),
                        result.getString("School_Name"),
                        result.getString("City"),
                        result.getInt("Number_of_Students"),
                        result.getInt("Number_of_Majors")
                ));
            }
        }catch (Exception e){
            System.out.println("Ka problem tek databaza");
        }
        return schools;

    }

}
