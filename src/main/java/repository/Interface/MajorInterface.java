package repository.Interface;

import Database.DBConnector;
import model.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MajorInterface {
     ArrayList<Major> getMajorBySchool(int school_id);

    public  Major getMajorByName(String major_name);

    public  Major getById(int major_id);

    public  ArrayList<Major> getAllMajors();


}
