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
import model.*;
import model.dto.CreateSchoolDto;
import model.dto.StudentDto;
import model.dto.UpdateGradeDto;
import repository.*;
import service.StudentService;
import service.Validator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminStudentController implements Initializable {

    @FXML
    private TableView<Students> StudentTable;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private TableColumn<Students, String> columnStudentEmail;

    @FXML
    private TableColumn<Students, String> columnStudentLastName;

    @FXML
    private TableColumn<Students, Integer> columnStudentID;

    @FXML
    private TableColumn<Students, String> columnStudentName;

    @FXML
    private TableColumn<Students, Integer> columnsStudentAddress;

    @FXML
    private TableColumn<Students, Integer > columnsStudentLevel;

    @FXML
    private TableColumn<Students, Integer> columnsStudentMajor;

    @FXML
    private TableColumn<Students, Integer> columnsStudentSchool;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private AnchorPane invisiblePane;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private ComboBox<String> periodComboBox;

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
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private ComboBox<String> comboBoxLevelFilter;

    @FXML
    private ComboBox<String> comboBoxMajorFilter;

    @FXML
    private ComboBox<String> conboBoxSchoolFilter;
    @FXML
    private TextField txtFilterName;

    @FXML
    void handleFilterClick(ActionEvent event) {

    }

    @FXML
    void handleAddClick(ActionEvent event) {
        StudentDto userSignUpData = new StudentDto(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText(),
                returnId(cityComboBox.getValue()),
                returnId(schoolComboBox.getValue()),
                returnId(majorComboBox.getValue()),
                returnId(periodComboBox.getValue()),
                this.getGender(event),
                java.sql.Date.valueOf(datePickerBirthday.getValue()));

        boolean response = StudentService.signUp(userSignUpData);

        if (response) {
            Navigator.navigate(event, Navigator.ADMIN_STUDENT_PAGE);
        }else {
            if(!Validator.firstNameValidate(txtFirstName.getText())){
                txtFirstName.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            }
            if(!Validator.lastNameValidate(txtLastName.getText())){
                txtLastName.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            }
            if(!Validator.emailValidate(txtEmail.getText())){
                txtEmail.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            }
            if(!Validator.birthdayValidate(java.sql.Date.valueOf(datePickerBirthday.getValue()))){
                datePickerBirthday.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            }
            if(!Validator.passwordValidate(pwdPassword.getText(), pwdConfirmPassword.getText())){
                pwdPassword.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
                pwdConfirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            }

        }



    }

    @FXML
    void handleNextClick(ActionEvent event) {
        invisiblePane.setVisible(true);

    }

    @FXML
    void handleDeleteClick(ActionEvent event) {

        Students selectedItem = StudentTable.getSelectionModel().getSelectedItem();


        if (selectedItem != null) {

//            boolean deleted = StudentRepository.delete(selectedItem.getId());
//
//
//            if (deleted) {
//                StudentTable.getItems().remove(selectedItem);
//            } else {
//                System.out.println("Please select a row to delete.");
//            }
        }

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
        this.ComboBoxInitialize();

        ObservableList<Students> students = FXCollections.observableArrayList(StudentRepository.getAllStudents());
        if (StudentTable != null) {
            columnStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnsStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            columnsStudentMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
            columnsStudentLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
            columnsStudentSchool.setCellValueFactory(new PropertyValueFactory<>("school"));

            StudentTable.setItems(students);
        } else {
            System.out.println("StudentTable is null.");
        }

    }


    public void ComboBoxInitialize(){
        cityComboBox.setValue("Address");
        schoolComboBox.setValue("School");
        majorComboBox.setValue("Major");
        periodComboBox.setValue("Level");
        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getId()+" "+city.getCity());
        }
        cityComboBox.getItems().addAll(cities);
        cityComboBox.setOnAction(this::handleCitySelection);

        ArrayList<String> levels= new ArrayList<>();
        for(Grade_level level: GradeLevelRepository.getAllLevels()){
            levels.add(level.getLevel_id()+" "+level.getLevel_name());
        }
        periodComboBox.getItems().addAll(levels);

    }

    private void handleCitySelection(ActionEvent event) {
        int id=returnId(cityComboBox.getValue());
        ArrayList<School> schools = SchoolRepository.getSchoolByCity(id);
        for(School s: schools){
            schoolComboBox.getItems().addAll(s.getId()+" "+s.getName());
        }
        schoolComboBox.setOnAction(this::handleSchoolSelection);
    }

    private void handleSchoolSelection(ActionEvent ae){
        int id=returnId(schoolComboBox.getValue());
        ArrayList<Major> majors= MajorRepository.getMajorBySchool(id);
        for(Major m:majors){
            majorComboBox.getItems().addAll(m.getId()+" "+m.getMajor_name());
        }
    }

    private int returnId(String select){
        String[] vargu=select.split(" ");
        int id=Integer.parseInt(vargu[0]);
        return id;
    }
}
