package service;

import model.School;
import model.School_Major;
import model.Students;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import repository.SchoolRepository;

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



    public static boolean addSchoolMajor(AddSchoolMajorDto userData){
        int school_id= userData.getSchool_id();
        int major_id=userData.getMajor_id();
        School_Major schoolMajor=SchoolRepository.getSchoolMajor(userData);
        if(!(schoolMajor==null)){
            return false;
        }
        return SchoolRepository.addSchoolMajor(userData);
    }

    public static ArrayList<School> getSchoolByCity(int city_id) {
        return SchoolRepository.getSchoolByCity(city_id);
    }

    public static School getById(int id){
        return SchoolRepository.getById(id);
    }
}
