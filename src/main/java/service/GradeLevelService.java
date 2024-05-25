package service;

import model.Grade_level;
import repository.GradeLevelRepository;
import service.Interface.GradeLevelInterface;

import java.util.ArrayList;

public class GradeLevelService implements GradeLevelInterface {
    GradeLevelRepository  GradeLevelRepository= new GradeLevelRepository();

    public ArrayList<Grade_level> getAllLevels() {
        return GradeLevelRepository.getAllLevels();
    }
    public Grade_level getLevelById(int id){return  GradeLevelRepository.getLevelById(id);}
}
