package model.dto;

//i merr te dhenat prej controllleri
public class ChangePasswordRequestDto {
    private int id;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    private String passwordHash;
    private String salt;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public int getId() {
        return id;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public ChangePasswordRequestDto(int id,String oldPassword, String newPassword, String confirmNewPassword, String passwordHash, String salt) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.id=id;
    }
}
