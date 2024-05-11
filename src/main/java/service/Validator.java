package service;

import model.dto.StudentDto;
import model.dto.UserDto;

public class Validator {


    public static boolean validate(UserDto userData){
        String firstName = userData.getFirstName();
        String lastName = userData.getLastName();
        String email = userData.getEmail();
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        boolean respond = nameValidate(firstName,lastName) && passwordValidate(password,confirmPassword) && emailValidate(email);

        return  respond;

      }

    public static boolean nameValidate(String firstName, String lastName){
        if (!firstName.matches("[a-zA-Z]+") && !lastName.matches("[a-zA-Z]+")) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean passwordValidate(String password, String confirmPassword){
        if(!password.equals(confirmPassword) && password.length() < 8){
            return false;
        }else {
            return true;
        }
    }

    public static boolean emailValidate(String email) {
        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

        // Check if email matches the pattern
        if (!email.matches(emailPattern)) {
            return false;
        } else if (!email.contains("@student") && !email.contains("@teacher")) {
            // Check if email contains either "@student" or "@teacher"
            return false;
        } else {
            return true;
        }
    }

}
