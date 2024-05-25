package service;

import model.Subject;
import repository.SubjectRepository;
import service.Interface.SubjectInterface;

import java.util.ArrayList;

public class SubjectService implements SubjectInterface {
    SubjectRepository SubjectRepository= new SubjectRepository();
    public ArrayList<Subject> getAllSubjects(){
        return SubjectRepository.getAllSubjects();
    }
}
