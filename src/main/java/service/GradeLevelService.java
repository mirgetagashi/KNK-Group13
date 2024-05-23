package service;

import model.Grade_level;
import repository.GradeLevelRepository;

import java.util.ArrayList;

public class GradeLevelService {

    public static ArrayList<Grade_level> getAllLevels() {
        return GradeLevelRepository.getAllLevels();
    }
    public static Grade_level getLevelById(int id){return  GradeLevelRepository.getLevelById(id);}
}
