package service;

import model.Students;
import model.dto.*;
import repository.StudentRepository;

import java.sql.SQLException;

public class StudentService {

    public static boolean signUp(StudentDto userData) {
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();


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
}

