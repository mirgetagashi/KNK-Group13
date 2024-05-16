package service;

import model.dto.StudentDto;
import model.dto.UserDto;

import java.util.Calendar;
import java.util.Date;

public class Validator {


    public static boolean validate(UserDto userData){
        String firstName = userData.getFirstName();
        String lastName = userData.getLastName();
        String email = userData.getEmail();
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();
        Date birthday = userData.getBirthday();


        boolean respond = firstNameValidate(firstName) && lastNameValidate(lastName) && passwordValidate(password,confirmPassword) && emailValidate(email)
                && birthdayValidate(birthday);

        return  respond;

    }

    public static boolean firstNameValidate(String firstName){
        if (!firstName.matches("[a-zA-Z]+")){
            return false;
        }else{
            return true;
        }
    }

    public static boolean lastNameValidate(String lastName){
        if (!lastName.matches("[a-zA-Z]+")){
            return false;
        }else{
            return true;
        }
    }


    public static boolean passwordValidate(String password, String confirmPassword){
        if(password.equals(confirmPassword) && password.length() > 8){
            return true;
        }else {
            return false;
        }
    }

    public static boolean emailValidate(String email) {
        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
        if (!email.matches(emailPattern)) {
            return false;
        } else if (!email.contains("@student") && !email.contains("@teacher")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean birthdayValidate(Date birthday){
        Calendar calBirthday = Calendar.getInstance();
        calBirthday.setTime(birthday);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < calBirthday.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age >= 14 && age <= 64;
    }


}
