package service;

import model.*;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import model.filter.SchoolFilter;
import repository.SchoolRepository;
import service.Interface.SchoolInterface;

import java.sql.SQLException;
import java.util.ArrayList;

public class SchoolService implements SchoolInterface {
    SchoolRepository SchoolRepository= new SchoolRepository();
    public boolean add(CreateSchoolDto userData){
        String name=userData.getName();
        if(!name.matches("[a-zA-Z ]+")){
            return false;
        }

        return SchoolRepository.create(userData);
    }


    public boolean delete(int id){
        return SchoolRepository.delete(id);
    }

    public ArrayList<SchoolTable> filterSchool(SchoolFilter filter) {
        try {
            return SchoolRepository.getByFilter(filter);
        } catch (SQLException e) {
            return null;
        }
    }




    public boolean addSchoolMajor(AddSchoolMajorDto userData){
        int school_id= userData.getSchool_id();
        int major_id=userData.getMajor_id();
        School_Major schoolMajor=SchoolRepository.getSchoolMajor(userData);
        if(!(schoolMajor==null)){
            return false;
        }
        return SchoolRepository.addSchoolMajor(userData);
    }

    public ArrayList<School> getAllSchools(){
        return SchoolRepository.getAllSchools();
    }
    public ArrayList<School> getSchoolByCity(int city_id) {
        return SchoolRepository.getSchoolByCity(city_id);
    }

    public School getById(int id){
        return SchoolRepository.getById(id);
    }
}
