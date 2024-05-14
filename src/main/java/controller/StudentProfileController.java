package controller;
import app.SessionManager.StudentSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import repository.AddressRepository;
import repository.SchoolRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {
    @FXML
    private Label address;

    @FXML
    private Label birthday;

    @FXML
    private Button cancel;

    @FXML
    private Label email;

    @FXML
    private Label firstName;

    @FXML
    private Label gender;

    @FXML
    private Label lastName;

    @FXML
    private Label level;

    @FXML
    private Button modify;

    @FXML
    private Label school;

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleModify(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText(StudentSession.getStudent().getFirstName());
        lastName.setText(StudentSession.getStudent().getLastName());
        gender.setText(StudentSession.getStudent().getGender());
        level.setText(String.valueOf(StudentSession.getStudent().getLevel()));
        school.setText(SchoolRepository.getById(StudentSession.getStudent().getSchool()).getName());
        email.setText(StudentSession.getStudent().getEmail());
        birthday.setText(String.valueOf(StudentSession.getStudent().getBirthday()));
        address.setText(AddressRepository.getById(StudentSession.getStudent().getAddress()).getCity());
    }
}
