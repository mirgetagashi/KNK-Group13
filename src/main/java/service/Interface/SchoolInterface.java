package service.Interface;

import model.School;
import model.SchoolTable;
import model.School_Major;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import model.filter.SchoolFilter;
import repository.SchoolRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SchoolInterface {

    boolean add(CreateSchoolDto userData);


    boolean delete(int id);

    ArrayList<SchoolTable> filterSchool(SchoolFilter filter);

    boolean addSchoolMajor(AddSchoolMajorDto userData);

    ArrayList<School> getAllSchools();
    ArrayList<School> getSchoolByCity(int city_id);

    School getById(int id);
}
