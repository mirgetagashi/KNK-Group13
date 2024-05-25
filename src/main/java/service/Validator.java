package service;

import model.Students;
import model.dto.StudentDto;
import repository.StudentRepository;

import java.util.Calendar;
import java.util.Date;

public class Validator {
    static StudentRepository StudentRepository= new StudentRepository();


    public static boolean validateStudent(StudentDto userData){
        return firstNameValidate(userData.getFirstName())
                && lastNameValidate(userData.getLastName())
                &&  passwordValidate(userData.getPassword(), userData.getConfirmPassword())
                && emailValidate(userData.getEmail()) && birthdayValidate(userData.getBirthday());
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
        Students user = StudentRepository.getByEmail(email);
        if (user == null) {
            return false;
        }
        if (!email.matches(emailPattern)) {
            return false;
        } else if (!email.contains("@student") && email.isEmpty()) {
            return false;
        }else {
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
        return age >= 14 && age <= 20;
    }


}
