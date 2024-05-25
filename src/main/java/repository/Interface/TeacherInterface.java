package repository.Interface;

import model.Teacher;
import model.dto.CreateTeacherDto;
import model.filter.TeacherFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherInterface {
    boolean create(CreateTeacherDto userData);


    boolean delete(int id) ;


    Teacher getByEmail(String email);

    Teacher getById(int std_id);

    ArrayList<Teacher> getAllTeachers();
    ArrayList<Teacher> getByFilter(TeacherFilter filter) throws SQLException;

}
