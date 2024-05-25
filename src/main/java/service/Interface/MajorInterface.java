package service.Interface;

import model.Major;
import repository.MajorRepository;

import java.util.ArrayList;

public interface MajorInterface {
    ArrayList<Major> getAllMajors();

    ArrayList<Major> getMajorBySchool(int school_id);

    Major getById(int id);
}
