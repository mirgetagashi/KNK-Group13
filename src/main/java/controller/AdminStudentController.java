package controller;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;
import model.dto.StudentDto;
import model.filter.StudentFilter;
import repository.*;
import service.*;
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
    private TableColumn<Students, Integer> columnsStudentLevel;

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
    private ComboBox<String> conboBoxSchoolFilter;

    @FXML
    private TextField txtFilterName;

    @FXML
    private Pagination pagination;

    private final static int rowsPerPage = 15;

    private ObservableList<Students> dataList;

    @FXML
    void handleFilterClick(ActionEvent event) {


        StudentFilter filter = new StudentFilter();
        filter.setName(this.txtFilterName.getText());
        filter.setLevel(this.comboBoxLevelFilter.getValue());
        filter.setSchool(this.conboBoxSchoolFilter.getValue());
        ArrayList<Students> filterStudents = StudentService.filterStudents(filter);
        if (filterStudents == null) {
            System.out.println("Error occurred, check filter code!");
        } else {
            this.updateTable(filterStudents);
        }


    }

    private void updateTable(ArrayList<Students> filteredStudents) {
        ObservableList<Students> filteredData = FXCollections.observableArrayList(filteredStudents);

        StudentTable.setItems(filteredData);
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
        } else {
            validateFields();
        }
    }

    private void validateFields() {
        if (!Validator.firstNameValidate(txtFirstName.getText())) {
            txtFirstName.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
        }
        if (!Validator.lastNameValidate(txtLastName.getText())) {
            txtLastName.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
        }
        if (!Validator.emailValidate(txtEmail.getText())) {
            txtEmail.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
        }
        if (!Validator.birthdayValidate(java.sql.Date.valueOf(datePickerBirthday.getValue()))) {
            datePickerBirthday.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
        }
        if (!Validator.passwordValidate(pwdPassword.getText(), pwdConfirmPassword.getText())) {
            pwdPassword.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
            pwdConfirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 0.5px;");
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
            // Delete logic goes here
        } else {
            System.out.println("Please select a row to delete.");
        }
    }

    @FXML
    void handleEditClick(ActionEvent event) {
        // Edit logic goes here
    }

    public String getGender(ActionEvent ae) {
        if (radioButtonMale.isSelected()) {
            return "M";
        } else if (radioButtonFemale.isSelected()) {
            return "F";
        }
        return "";
    }

    private TableView<Students> createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
        StudentTable.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
        return StudentTable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.ComboBoxInitialize();
        this.FilterComboBoxInitialize();

        ComboBoxInitialize();


        dataList = FXCollections.observableArrayList(StudentService.getAllStudents());

        columnStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnsStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address_id"));
        columnsStudentMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
        columnsStudentLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        columnsStudentSchool.setCellValueFactory(new PropertyValueFactory<>("school"));

        pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));
        pagination.setPageFactory(this::createPage);
    }


    public void FilterComboBoxInitialize() {
        comboBoxLevelFilter.setValue("Level");
        conboBoxSchoolFilter.setValue("School");

        ArrayList<String> levels = new ArrayList<>();
        for (Grade_level level : GradeLevelService.getAllLevels()) {
            levels.add(level.getLevel_id() + " " + level.getLevel_name());
        }
        comboBoxLevelFilter.getItems().addAll(levels);

        ArrayList<String> schools = new ArrayList<>();
        for (School s : SchoolRepository.getAllSchools()) {
            schools.add(s.getId() + " " + s.getName());
        }
        conboBoxSchoolFilter.getItems().addAll(schools);

    }


    public void ComboBoxInitialize() {


            cityComboBox.setValue("Address");
            schoolComboBox.setValue("School");
            majorComboBox.setValue("Major");
            periodComboBox.setValue("Level");

            ArrayList<String> cities = new ArrayList<>();
            for (Address city : AddressService.getAllCities()) {
                cities.add(city.getId() + " " + city.getCity());
            }
            cityComboBox.getItems().addAll(cities);
            cityComboBox.setOnAction(this::handleCitySelection);

            ArrayList<String> levels = new ArrayList<>();
            for (Grade_level level : GradeLevelService.getAllLevels()) {
                levels.add(level.getLevel_id() + " " + level.getLevel_name());
            }
            periodComboBox.getItems().addAll(levels);
        }

        private void handleCitySelection (ActionEvent event){
            int id = returnId(cityComboBox.getValue());
            ArrayList<School> schools = SchoolService.getSchoolByCity(id);
            schoolComboBox.getItems().clear();
            for (School s : schools) {
                schoolComboBox.getItems().add(s.getId() + " " + s.getName());
            }
            schoolComboBox.setOnAction(this::handleSchoolSelection);
        }

        private void handleSchoolSelection (ActionEvent ae){
            int id = returnId(schoolComboBox.getValue());
            ArrayList<Major> majors = MajorService.getMajorBySchool(id);
            majorComboBox.getItems().clear();
            for (Major m : majors) {
                majorComboBox.getItems().add(m.getId() + " " + m.getMajor_name());
            }
        }

        private int returnId (String select){
            String[] vargu = select.split(" ");
            return Integer.parseInt(vargu[0]);
        }
    }





//package controller;
//
//import app.Navigator;
//import app.SessionManager.TeacherSession;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import model.*;
//import model.dto.CreateSchoolDto;
//import model.dto.StudentDto;
//import model.dto.TeacherGradeDeleteDto;
//import model.dto.UpdateGradeDto;
//import model.filter.StudentFilter;
//import repository.*;
//import service.*;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class AdminStudentController implements Initializable {
//
//    @FXML
//    private TableView<Students> StudentTable;
//
//    @FXML
//    private ComboBox<String> cityComboBox;
//
//    @FXML
//    private TableColumn<Students, String> columnStudentEmail;
//
//    @FXML
//    private TableColumn<Students, String> columnStudentLastName;
//
//    @FXML
//    private TableColumn<Students, Integer> columnStudentID;
//
//    @FXML
//    private TableColumn<Students, String> columnStudentName;
//
//    @FXML
//    private TableColumn<Students, Integer> columnsStudentAddress;
//
//    @FXML
//    private TableColumn<Students, Integer > columnsStudentLevel;
//
//    @FXML
//    private TableColumn<Students, Integer> columnsStudentMajor;
//
//    @FXML
//    private TableColumn<Students, Integer> columnsStudentSchool;
//
//    @FXML
//    private DatePicker datePickerBirthday;
//
//    @FXML
//    private AnchorPane invisiblePane;
//
//    @FXML
//    private ComboBox<String> majorComboBox;
//
//    @FXML
//    private ComboBox<String> periodComboBox;
//
//    @FXML
//    private PasswordField pwdConfirmPassword;
//
//    @FXML
//    private PasswordField pwdPassword;
//
//    @FXML
//    private RadioButton radioButtonFemale;
//
//    @FXML
//    private RadioButton radioButtonMale;
//
//    @FXML
//    private ComboBox<String> schoolComboBox;
//
//    @FXML
//    private TextField txtEmail;
//
//    @FXML
//    private TextField txtFirstName;
//
//    @FXML
//    private TextField txtLastName;
//
//    @FXML
//    private ComboBox<String> comboBoxLevelFilter;
//
//    @FXML
//    private ComboBox<String> conboBoxSchoolFilter;
//    @FXML
//    private TextField txtFilterName;
//    @FXML
//    private Pagination pagination;
//    private final static int rowsPerPage = 15;
//
//    private ObservableList<Students> dataList;
//
//    @FXML
//    void handleFilterClick(ActionEvent event) {
//
//        StudentFilter filter = new StudentFilter();
//        filter.setName(this.txtFilterName.getText());
//        filter.setLevel(this.comboBoxLevelFilter.getValue());
//        filter.setSchool(this.conboBoxSchoolFilter.getValue());
//        ArrayList<Students> filterStudents = StudentService.filterStudents(filter);
//        if(filterStudents == null){
//            System.out.println("Error occurred, check filter code!");
//        }else{
//            this.updateTable(filterStudents);
//        }
//
//    }
//
//    private void updateTable(ArrayList<Students> filteredStudents) {
//        ObservableList<Students> filteredData = FXCollections.observableArrayList(filteredStudents);
//
//        StudentTable.setItems(filteredData);
//    }
//
//
//
//
//    @FXML
//    void handleNextClick(ActionEvent event) {
//        invisiblePane.setVisible(true);
//
//    }
//
//    @FXML
//    void handleDeleteClick(ActionEvent event) {
//
//        Students selectedItem = StudentTable.getSelectionModel().getSelectedItem();
//
//
//        if (selectedItem != null) {
//
//            boolean deleted = StudentService.delete(selectedItem.getId());
//
//
//            if (deleted) {
//                StudentTable.getItems().remove(selectedItem);
//            } else {
//                System.out.println("Please select a row to delete.");
//            }
//        }
//
//    }
//
//
//
//
//
//    @FXML
//    void handleEditClick(ActionEvent event) {
//
//
//
//    }
//
//
//
//
//    public String getGender(ActionEvent ae){
//        String  genderSelect;
//        if(radioButtonMale.isSelected()){
//            genderSelect="M";
//        }else if(radioButtonFemale.isSelected()){
//            genderSelect="F";
//        }else {
//            genderSelect="";
//        }
//        return genderSelect;
//    }
//
//    private TableView<Students> createPage(int pageIndex) {
//        int fromIndex = pageIndex * rowsPerPage;
//        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
//        StudentTable.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
//        return StudentTable;
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.ComboBoxInitialize();
//        this.FilterComboBoxInitialize();
//
//        dataList = FXCollections.observableArrayList(StudentService.getAllStudents());
//
//        columnStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        columnStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        columnStudentID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        columnsStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address_id"));
//        columnsStudentMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
//        columnsStudentLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
//        columnsStudentSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
//
//        pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));
//        pagination.setPageFactory(this::createPage);
//
//    }
//
//    public void FilterComboBoxInitialize(){
//        comboBoxLevelFilter.setValue("Level");
//        conboBoxSchoolFilter.setValue("School");
//
//        ArrayList<String> levels= new ArrayList<>();
//        for(Grade_level level: GradeLevelService.getAllLevels()){
//            levels.add(level.getLevel_id()+" "+level.getLevel_name());
//        }
//        comboBoxLevelFilter.getItems().addAll(levels);
//
//        ArrayList<String> schools = new ArrayList<>();
//        for(School s: SchoolRepository.getAllSchools()){
//            schools.add(s.getId()+" "+s.getName());
//        }
//        conboBoxSchoolFilter.getItems().addAll(schools);
//
//    }
//
//
//    public void ComboBoxInitialize(){
//        cityComboBox.setValue("Address");
//        schoolComboBox.setValue("School");
//        majorComboBox.setValue("Major");
//        periodComboBox.setValue("Level");
//        ArrayList<String> cities= new ArrayList<>();
//        for (Address city : (AddressService.getAllCities())) {
//            cities.add(city.getId()+" "+city.getCity());
//        }
//        cityComboBox.getItems().addAll(cities);
//        cityComboBox.setOnAction(this::handleCitySelection);
//
//        ArrayList<String> levels= new ArrayList<>();
//        for(Grade_level level: GradeLevelService.getAllLevels()){
//            levels.add(level.getLevel_id()+" "+level.getLevel_name());
//        }
//        periodComboBox.getItems().addAll(levels);
//
//    }
//
//    private void handleCitySelection(ActionEvent event) {
//        int id=returnId(cityComboBox.getValue());
//        ArrayList<School> schools = SchoolService.getSchoolByCity(id);
//        for(School s: schools){
//            schoolComboBox.getItems().addAll(s.getId()+" "+s.getName());
//        }
//        schoolComboBox.setOnAction(this::handleSchoolSelection);
//    }
//
//    private void handleSchoolSelection(ActionEvent ae){
//        int id=returnId(schoolComboBox.getValue());
//        ArrayList<Major> majors= MajorService.getMajorBySchool(id);
//        for(Major m:majors){
//            majorComboBox.getItems().addAll(m.getId()+" "+m.getMajor_name());
//        }
//    }
//
//    private int returnId(String select){
//        String[] vargu=select.split(" ");
//        int id=Integer.parseInt(vargu[0]);
//        return id;
//    }
//}
