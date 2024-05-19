package service;

import model.Administrator;
import model.Students;
import model.dto.LoginUserDto;
import repository.AdminRepository;
import repository.StudentRepository;

public class AdminService {

    public static boolean login(LoginUserDto loginData){
        Administrator user = AdminRepository.getByEmail(loginData.getEmail());
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
    public static Administrator getByEmail(String email){
        return AdminRepository.getByEmail(email);
    }

}
