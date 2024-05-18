package controller;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Address;
import model.School;
import model.Subject;
import model.Teacher;
import model.dto.TeacherDto;
import repository.AddressRepository;
import repository.SchoolRepository;
import repository.SubjectRepository;
import repository.TeacherRepository;
import service.TeacherService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminTeacherController implements Initializable {

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private TableView<Teacher> TeachersTable;

    @FXML
    private TableColumn<Teacher, Integer> columnAddress;

    @FXML
    private TableColumn<Teacher, Date> columnBirthday;

    @FXML
    private TableColumn<Teacher, String> columnEmail;

    @FXML
    private TableColumn<Teacher, String > columnFirstName;

    @FXML
    private TableColumn<Teacher, Integer> columnID;

    @FXML
    private TableColumn<Teacher, String> columnLastName;

    @FXML
    private TableColumn<Teacher, String> columnSchool;

    @FXML
    private TableColumn<Teacher, String> columnSubject;

    @FXML
    private TableColumn<Teacher, String> columnTitle;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private ComboBox<String> schoolComboBox;

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;
    @FXML
    private ComboBox<String> titleComboBox;

    @FXML
    private TextField txtEducation;
    @FXML
    private AnchorPane invisiblePane;

    @FXML
    void handleAddClick(ActionEvent event) {
        int cityID=returnId(cityComboBox.getValue());
        int schoolID=returnId(schoolComboBox.getValue());
        int subjectID=returnId(subjectComboBox.getValue());


        TeacherDto userSignUpData = new TeacherDto(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText(),
                AddressRepository.getById(cityID),
                SchoolRepository.getById(schoolID),
                SubjectRepository.getById(subjectID),
                this.txtEducation.getText(),
                titleComboBox.getValue(),
                java.sql.Date.valueOf(datePickerBirthday.getValue()),
                this.getGender(event)
        );

        boolean response = TeacherService.signUp(userSignUpData);

        if (response) {
            Navigator.navigate(event, Navigator.ADMIN_TEACHER_PAGE);
        }

    }

    @FXML
    void handleDeleteClick(ActionEvent event) {
        Teacher selectedItem = TeachersTable.getSelectionModel().getSelectedItem();


        if (selectedItem != null) {

            boolean deleted = TeacherRepository.delete(selectedItem.getId());


            if (deleted) {
                TeachersTable.getItems().remove(selectedItem);
            } else {
                System.out.println("Please select a row to delete.");
            }
        }
    }

    @FXML
    void handleNextClick(ActionEvent event) {
        invisiblePane.setVisible(true);

    }

    @FXML
    void handleEditClick(ActionEvent event) {

    }


    public String getGender(ActionEvent ae){
        String  genderSelect;
        if(radioButtonMale.isSelected()){
            genderSelect="M";
        }else if(radioButtonFemale.isSelected()){
            genderSelect="F";
        }else {
            genderSelect="";
        }
        return genderSelect;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<Teacher> teachers = FXCollections.observableArrayList(TeacherRepository.getAllTeachers());
        if (TeachersTable != null) {
            columnAddress.setCellValueFactory(new PropertyValueFactory<>("address_id"));
            columnBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
            columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnSubject.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
            columnSchool.setCellValueFactory(new PropertyValueFactory<>("school_id"));
            columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

            TeachersTable.setItems(teachers);
        } else {
            System.out.println("StudentTable is null.");
        }


        cityComboBox.setValue("Address");
        schoolComboBox.setValue("School");
        subjectComboBox.setValue("Subject");
        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getId()+" "+city.getCity());
        }
        cityComboBox.getItems().addAll(cities);
        cityComboBox.setOnAction(this::handleCitySelection);

        ArrayList<String > subjects= new ArrayList<>();
        for(Subject subject: SubjectRepository.getAllSubjects()){
            subjects.add(subject.getId()+" "+subject.getName());
        }
        subjectComboBox.getItems().addAll(subjects);

        String[] titles={"Bsc","Msc","Phd"};
        titleComboBox.getItems().addAll(titles);
    }



    private void handleCitySelection(ActionEvent event) {
        int id=returnId(cityComboBox.getValue());
        ArrayList<School> schools = SchoolRepository.getSchoolByCity(id);
        for(School s: schools){
            schoolComboBox.getItems().addAll(s.getId()+" "+s.getName());
        }


    }

    private int returnId(String select){
        String[] vargu=select.split(" ");
        int id=Integer.parseInt(vargu[0]);
        return id;
    }

}
