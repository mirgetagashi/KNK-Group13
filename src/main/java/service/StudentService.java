package service;

import model.Students;
import model.dto.*;
import model.filter.StudentFilter;
import repository.GradeRepository;
import repository.StudentRepository;
import repository.TeacherDashboardRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StudentService {

    public static boolean signUp(StudentDto userData) {
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        String email= userData.getEmail();

        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );

        CreateStudentDto createStudentData = new CreateStudentDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                salt,
                passwordHash,
                userData.getAddress(),
                userData.getSchool(),
                userData.getMajor(),
                userData.getPeriod(),
                userData.getGender(),
                userData.getBirthday()
        );

        return StudentRepository.create(createStudentData);
    }

    public static boolean login(LoginUserDto loginData) {
        Students user = StudentRepository.getByEmail(loginData.getEmail());
        if (user == null) {
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }
    public static ArrayList<Students> filterStudents(StudentFilter filter){
        try{

            return StudentRepository.getByFilter(filter);
        }catch (SQLException e){
            return null;
        }
    }

    public static ArrayList<Students> filter(StudentFilter filter) throws SQLException {
        return StudentRepository.getByFilter(filter);
    }

    public static boolean update(UpdateStudentDto userData){
        return StudentRepository.update(userData);
    }

    public static Students getById(int id){
        return StudentRepository.getById(id);
    }


    public static boolean changePassword(ChangePasswordRequestDto userData){
        int id= userData.getId();
        String salt= userData.getSalt();
        String passwordHash= userData.getPasswordHash();
        String oldPassword= userData.getOldPassword();
        String newPassword= userData.getNewPassword();
        String confirmNewPassword= userData.getConfirmNewPassword();

        String oldPasswordHash=PasswordHasher.generateSaltedHash(oldPassword,salt);
        String newPasswordHash=PasswordHasher.generateSaltedHash(newPassword,salt);
        if(!oldPasswordHash.equals(passwordHash)){
            return false;
        }

        if(!newPassword.equals(confirmNewPassword)){
            return false;
        }

        if(newPasswordHash.equals(oldPasswordHash)){
            return false;
        }

        ChangePasswordDto dto= new ChangePasswordDto(id, newPasswordHash);

        return StudentRepository.updatePassword(dto);
    }
    public static List<Integer> getGradesByStudent(int std_id){
        return GradeRepository.getGradesByStudent(std_id);
    }
    public static ArrayList<Students> getAllStudents(){
        return StudentRepository.getAllStudents();
    }

    public static boolean delete(int id){
        return StudentRepository.delete(id);
    }
    public static Students getByEmail(String email){
        return StudentRepository.getByEmail(email);
    }

}

