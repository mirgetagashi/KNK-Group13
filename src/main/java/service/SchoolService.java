package service;

import model.*;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import model.filter.SchoolFilter;
import repository.SchoolRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class SchoolService {
    public static boolean add(CreateSchoolDto userData){
        String name=userData.getName();
        if(!name.matches("[a-zA-Z ]+")){
            return false;
        }

        return SchoolRepository.create(userData);
    }

    public static boolean delete(int id){
        return SchoolRepository.delete(id);
    }

    public static ArrayList<SchoolTable> filterSchool(SchoolFilter filter){
        try{

            return SchoolRepository.getByFilter(filter);
        }catch (SQLException e){
            return null;
        }
    }



    public static boolean addSchoolMajor(AddSchoolMajorDto userData){
        int school_id= userData.getSchool_id();
        int major_id=userData.getMajor_id();
        School_Major schoolMajor=SchoolRepository.getSchoolMajor(userData);
        if(!(schoolMajor==null)){
            return false;
        }
        return SchoolRepository.addSchoolMajor(userData);
    }

    public static ArrayList<School> getAllSchools(){
        return SchoolRepository.getAllSchools();
    }
    public static ArrayList<School> getSchoolByCity(int city_id) {
        return SchoolRepository.getSchoolByCity(city_id);
    }

    public static School getById(int id){
        return SchoolRepository.getById(id);
    }
}
