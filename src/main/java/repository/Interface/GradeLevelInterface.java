package repository.Interface;

import model.Grade_level;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GradeLevelInterface {
    Grade_level getLevelByName(String level) throws SQLException;
    ArrayList<Grade_level> getAllLevels() throws SQLException;
    Grade_level getLevelById(int id) throws SQLException;
}
