package service;

import model.Subject;
import repository.SubjectRepository;
import java.util.ArrayList;

public class SubjectService {
    public static ArrayList<Subject> getAllSubjects(){
        return SubjectRepository.getAllSubjects();
    }
}
