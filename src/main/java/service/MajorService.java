package service;

import model.Major;
import repository.MajorRepository;

import java.util.ArrayList;

public class MajorService {

    public static ArrayList<Major> getAllMajors(){
        return MajorRepository.getAllMajors();
    }

    public static ArrayList<Major> getMajorBySchool(int school_id){
        return MajorRepository.getMajorBySchool(school_id);
    }
}
