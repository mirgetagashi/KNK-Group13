package service;

import model.Students;
import model.dto.CreateStudentDto;
import model.dto.LoginUserDto;
import model.dto.StudentDto;
import repository.StudentRepository;

public class StudentService {

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
    public static boolean login(LoginUserDto loginData){
        Students user = StudentRepository.getByEmail(loginData.getEmail());
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
}
