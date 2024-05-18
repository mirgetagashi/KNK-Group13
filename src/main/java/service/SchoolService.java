package service;

import model.School_Major;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import repository.SchoolRepository;

public class SchoolService {
    public static boolean add(CreateSchoolDto userData){
        String name=userData.getName();
        if(!name.matches("[a-zA-Z ]+")){
            return false;
        }

        return SchoolRepository.create(userData);
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
}
