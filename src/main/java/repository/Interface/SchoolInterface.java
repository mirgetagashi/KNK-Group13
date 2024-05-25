package repository;

import model.School;
import model.SchoolTable;
import model.School_Major;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import model.filter.SchoolFilter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SchoolInterface {
    boolean create(CreateSchoolDto userData) throws SQLException;
    ArrayList<SchoolTable> getByFilter(SchoolFilter filter) throws SQLException;
    boolean delete(int id) throws SQLException;
    ArrayList<School> getSchoolByCity(int city_id) throws SQLException;
    ArrayList<School> getAllSchools() throws SQLException;
    boolean addSchoolMajor(AddSchoolMajorDto userData) throws SQLException;
    School_Major getSchoolMajor(AddSchoolMajorDto userData) throws SQLException;
    School getById(int school_id) throws SQLException;
}