package model.dto;

public class ChangePasswordDto {
    private  int id;

    public ChangePasswordDto(int id, String newHashPassword) {
        this.id = id;
        this.newHashPassword = newHashPassword;
    }
    public int getId() {
        return id;
    }
    public String getNewHashPassword() {
        return newHashPassword;
    }
    private String newHashPassword;

}
