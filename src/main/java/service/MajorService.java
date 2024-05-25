package service;

import model.Major;
import repository.MajorRepository;
import service.Interface.MajorInterface;

import java.util.ArrayList;

public class MajorService implements MajorInterface {
    MajorRepository MajorRepository= new MajorRepository();

    public ArrayList<Major> getAllMajors(){
        return MajorRepository.getAllMajors();
    }

    public ArrayList<Major> getMajorBySchool(int school_id){
        return MajorRepository.getMajorBySchool(school_id);
    }

    public Major getById(int id){
        return MajorRepository.getById(id);
    }
}
