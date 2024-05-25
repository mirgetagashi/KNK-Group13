package service.Interface;

import model.Grade_level;
import repository.GradeLevelRepository;

import java.util.ArrayList;

public interface GradeLevelInterface {

    ArrayList<Grade_level> getAllLevels();
    Grade_level getLevelById(int id);
}
