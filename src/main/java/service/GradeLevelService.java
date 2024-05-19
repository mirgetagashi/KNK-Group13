package service;

import model.Grade_level;
import repository.GradeLevelRepository;

import java.util.ArrayList;

public class GradeLevelService {

    public static ArrayList<Grade_level> getAllLevels() {
        return GradeLevelRepository.getAllLevels();
    }
}
