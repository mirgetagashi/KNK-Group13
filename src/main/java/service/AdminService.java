package service;

import model.Administrator;
import model.Students;
import model.dto.LoginUserDto;
import repository.AdminRepository;
import repository.StudentRepository;
import service.Interface.AdminInterface;

public class AdminService implements AdminInterface {
    AdminRepository AdminRepository= new AdminRepository();

    public boolean login(LoginUserDto loginData){
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
    public Administrator getByEmail(String email){
        return AdminRepository.getByEmail(email);
    }

}
