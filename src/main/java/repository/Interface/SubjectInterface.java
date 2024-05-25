package repository.Interface;

import model.Subject;

import java.util.ArrayList;

public interface SubjectInterface {
    ArrayList<Subject> getAllSubjects();

    Subject getSubjectByName(String subject);

    Subject getById(int subject_id);
}