package service;

import model.Teacher;
import model.dto.*;
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
}
