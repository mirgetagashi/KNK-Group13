package service.Interface;

import model.Administrator;
import model.dto.LoginUserDto;

public interface AdminInterface {
    boolean login(LoginUserDto loginData);
    Administrator getByEmail(String email);
}
