package service;

import model.dto.CreateSchoolDto;
import repository.SchoolRepository;

public class SchoolService {
    public static boolean addSchool(CreateSchoolDto userData){
        String name=userData.getName();
        if(!name.matches("[a-zA-Z]+")){
            return false;
        }

        return SchoolRepository.create(userData);
    }
}
