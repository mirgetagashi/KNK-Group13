package controller;

import app.Navigator;
import app.SessionManager.StudentSession;
import app.SessionManager.TeacherSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.*;

import java.net.URL;
import java.util.ResourceBundle;

public class teacherProfileController implements Initializable {

    @FXML
    private Label address;

    @FXML
    private Label birthday;

    @FXML
    private Label email;

    @FXML
    private Label firstName;

    @FXML
    private Label gender;

    @FXML
    private Label lastName;



    @FXML
    private Label school;

    @FXML
    private Text txtChangePassword;
    SchoolService SchoolService= new SchoolService();
    AddressService AddressService= new AddressService();

    @FXML
    void handleChangePassword(MouseEvent event) {
        Navigator.navigate(event,Navigator.FORGET_PASSWORD);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText(TeacherSession.getTeacher().getFirstName());
        lastName.setText(TeacherSession.getTeacher().getLastName());
        gender.setText(TeacherSession.getTeacher().getGender());

        school.setText(SchoolService.getById(TeacherSession.getTeacher().getSchool_id()).getName());
        email.setText(TeacherSession.getTeacher().getEmail());
        birthday.setText(String.valueOf(TeacherSession.getTeacher().getBirthday()));
        address.setText(AddressService.getById(TeacherSession.getTeacher().getAddress_id()).getCity());
    }

}