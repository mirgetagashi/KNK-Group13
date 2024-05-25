package controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import app.Navigator;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.*;
import model.dto.StudentDto;
import model.dto.TeacherGradeUpdateDto;
import model.dto.UpdateStudentDto;
import model.filter.StudentFilter;
import repository.*;
import service.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
            boolean delete=StudentService.delete(selectedItem.getId());
            if(delete){
                StudentTable.getItems().remove(selectedItem);
            }

        } else {
            System.out.println("Please select a row to delete.");
        }
    }

    @FXML
    void handleEditClick(ActionEvent event) {
        Students selectedItem = StudentTable.getSelectionModel().getSelectedItem();
        String firstName= this.txtFirstName.getText();
        String lastName= this.txtLastName.getText();
        int city_id= returnId(cityComboBox.getValue());
        int school_id=returnId(schoolComboBox.getValue());
        int major_id=returnId(majorComboBox.getValue());
        int level_id=returnId(periodComboBox.getValue());

        if (selectedItem != null) {
            int studentId = selectedItem.getId();
            UpdateStudentDto updateDto= new UpdateStudentDto(
                    studentId,
                    firstName,
                    lastName,
                    city_id,
                    school_id,
                    major_id,
                    level_id
            );

            boolean updated = StudentService.update(updateDto);
            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "The student information was successfully updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the student information.");
            }


        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    private void addSelection(){
        StudentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtFirstName.setText(String.valueOf(newSelection.getFirstName()));
                txtLastName.setText(String.valueOf(newSelection.getLastName()));
                Date date = newSelection.getBirthday();
                LocalDate localDate = date.toLocalDate();
                datePickerBirthday.setValue(localDate);
                cityComboBox.setValue(newSelection.getAddress_id()+" "+AddressService.getById(newSelection.getAddress_id()).getCity());
                schoolComboBox.setValue(newSelection.getSchool()+" "+SchoolService.getById(newSelection.getSchool()).getName());
                majorComboBox.setValue(newSelection.getMajor()+" "+MajorRepository.getById(newSelection.getMajor()).getMajor_name());
                periodComboBox.setValue(newSelection.getLevel()+" "+GradeLevelService.getLevelById(newSelection.getLevel()).getLevel_name());


            } else {
                System.out.println("Selection is null"); // Debugging line
            }
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.addSelection();

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



