package service.Interface;

import model.Teacher;
import model.dto.CreateTeacherDto;
import model.dto.LoginUserDto;
import model.dto.TeacherDto;
import model.filter.TeacherFilter;
import repository.TeacherRepository;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherInterface {
    boolean login(LoginUserDto loginData);

     boolean signUp(TeacherDto teacherData);

    boolean delete(int id);

    ArrayList<Teacher> filterTeacher(TeacherFilter filter);

    ArrayList<Teacher> getAllTeachers();

    Teacher getByEmail(String email);

}
