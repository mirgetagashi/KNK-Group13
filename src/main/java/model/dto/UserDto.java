package model.dto;

import java.time.LocalDate;
import java.util.Date;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String gender;
    private Date birthday;

    private UserDto(){}

    private UserDto(String firstName, String lastName, String email, String password, String confirmPassword, Date birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender=gender;
        this.birthday=birthday;
    }

    public static UserDto getInstance(String firstName, String lastName, String email, String password, String confirmPassword, String gender, Date birthday){
        return new UserDto(firstName,lastName,email,password,confirmPassword, birthday,gender);
    }
    public static UserDto getInstance(){
        return new UserDto();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(String  gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
