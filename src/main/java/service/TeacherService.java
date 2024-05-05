package service;

import model.Teacher;
import model.dto.CreateStudentDto;
import model.dto.LoginUserDto;
import model.dto.StudentDto;
import repository.StudentRepository;
import repository.TeacherRepository;

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

    public static boolean signUp(StudentDto userData){
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            return false;
        }

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
                userData.getPeriod()
        );

        return StudentRepository.create(createStudentData);
    }
}
