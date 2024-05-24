package controller;
import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnector;
import service.PasswordHasher;

public class ResetPasswordController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button resetButton;
    @FXML
    private Button cancelButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private void initialize() {
        resetButton.setOnAction(event -> handleResetPassword());
        cancelButton.setOnAction(event -> handleCancel());
    }
    @FXML
    private void handleCancel() {
        // Navigate back to login page
        Navigator.navigate(rootPane, Navigator.LOGIN_PAGE);
    }
    @FXML
    private void handleResetPassword() {
        String email = emailField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            showAlert("Password Mismatch", "The new password and confirm password do not match.");
            return;
        }

        // Check if email exists in the database
        if (!isEmailExist(email)) {
            showAlert("Email Not Found", "The email address does not exist in the database.");
            return;
        }

        // Reset password logic
        if (updatePasswordInDatabase(email, newPassword)) {
            showAlert("Password Reset", "Your password has been reset successfully.");
            // Clear fields
            emailField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
            Navigator.navigate(rootPane, Navigator.LOGIN_PAGE);
        } else {
            showAlert("Password Reset Failed", "Failed to reset your password. Please try again later.");
        }
    }

    private boolean isEmailExist(String email) {
        boolean studentExists = false;
        boolean teacherExists = false;
        try (Connection connection = DBConnector.getConnection()) {
            // Check if the email exists in the Students table
            try (PreparedStatement studentStatement = connection.prepareStatement("SELECT 1 FROM Students WHERE email = ?")) {
                studentStatement.setString(1, email);
                try (ResultSet studentResultSet = studentStatement.executeQuery()) {
                    studentExists = studentResultSet.next();
                }
            }
            // Check if the email exists in the Teachers table
            try (PreparedStatement teacherStatement = connection.prepareStatement("SELECT 1 FROM Teachers WHERE email = ?")) {
                teacherStatement.setString(1, email);
                try (ResultSet teacherResultSet = teacherStatement.executeQuery()) {
                    teacherExists = teacherResultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentExists || teacherExists;
    }

    private boolean updatePasswordInDatabase(String email, String newPassword) {
        // Generate salt and hash the new password
        String salt = PasswordHasher.generateSalt();
        String saltedHash = PasswordHasher.generateSaltedHash(newPassword, salt);

        try (Connection connection = DBConnector.getConnection()) {
            // Update the password in the Students table if the email is matched
            try (PreparedStatement studentStatement = connection.prepareStatement("UPDATE Students SET passwordHash = ?, salt = ? WHERE email = ?")) {
                studentStatement.setString(1, saltedHash);
                studentStatement.setString(2, salt);
                studentStatement.setString(3, email);
                int studentRowsAffected = studentStatement.executeUpdate();
                if (studentRowsAffected > 0) {
                    return true; // Password updated successfully in the Students table
                }
            }

            // Update the password in the Teachers table if the email is matched
            try (PreparedStatement teacherStatement = connection.prepareStatement("UPDATE Teachers SET passwordHash = ?, salt = ? WHERE email = ?")) {
                teacherStatement.setString(1, saltedHash);
                teacherStatement.setString(2, salt);
                teacherStatement.setString(3, email);
                int teacherRowsAffected = teacherStatement.executeUpdate();
                if (teacherRowsAffected > 0) {
                    return true; // Password updated successfully in the Teachers table
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Password update failed
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleResetPassword();
        }
    }
}
