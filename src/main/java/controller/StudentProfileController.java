package controller;
import app.Navigator;
import app.SessionManager.StudentSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import service.AddressService;
import service.SchoolService;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    AddressService AddressService= new AddressService();
    SchoolService SchoolService = new SchoolService();

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleModify(ActionEvent event) {

    }

    @FXML
    private Text txtChangePassword;

    @FXML
    void handleChangePassword(MouseEvent event) {

        Navigator.navigate(event,Navigator.STUDENT_SETTINGS);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setText(StudentSession.getStudent().getFirstName());
        lastName.setText(StudentSession.getStudent().getLastName());
        gender.setText(StudentSession.getStudent().getGender());
        level.setText(String.valueOf(StudentSession.getStudent().getLevel()));
        school.setText(SchoolService.getById(StudentSession.getStudent().getSchool()).getName());
        email.setText(StudentSession.getStudent().getEmail());
        birthday.setText(String.valueOf(StudentSession.getStudent().getBirthday()));
        address.setText(AddressService.getById(StudentSession.getStudent().getAddress_id()).getCity());
    }
}
