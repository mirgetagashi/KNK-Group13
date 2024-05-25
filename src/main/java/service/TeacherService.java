package service;

import model.Students;
import model.Teacher;
import model.dto.*;
import model.filter.StudentFilter;
import model.filter.TeacherFilter;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherService {

    public static boolean login(LoginUserDto loginData){
        Teacher user = TeacherRepository.getByEmail(loginData.getEmail());
        if(user == null){
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }

    public static boolean signUp(TeacherDto teacherData){
        String password = teacherData.getPassword();
        String confirmPassword = teacherData.getConfirmPassword();


        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateTeacherDto createTeacherDto = new CreateTeacherDto(
                teacherData.getFirstName(),
                teacherData.getLastName(),
                teacherData.getEmail(),
                salt,
                passwordHash,
                teacherData.getAddress(),
                teacherData.getEducation(),
                teacherData.getSchool(),
                teacherData.getSubject(),
                teacherData.getLevel(),
                teacherData.getGender(),
                teacherData.getBirthday()
        );

        return TeacherRepository.create(createTeacherDto);
    }

    public static boolean delete(int id) {
        return TeacherRepository.delete(id);
    }

    public static ArrayList<Teacher> filterTeacher(TeacherFilter filter){
        try{

            return TeacherRepository.getByFilter(filter);
        }catch (SQLException e){
            return null;
        }
    }

    public static ArrayList<Teacher> getAllTeachers(){
        return TeacherRepository.getAllTeachers();
    }

    public static Teacher getByEmail(String email){
        return TeacherRepository.getByEmail(email);
    }
}
