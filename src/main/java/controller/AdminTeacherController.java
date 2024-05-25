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
import model.dto.StudentTeacherDto;
import model.dto.TeacherDto;
import model.filter.StudentFilter;
import model.filter.TeacherFilter;
import repository.AddressRepository;
import repository.SchoolRepository;
import repository.SubjectRepository;
import repository.TeacherRepository;
import service.GradeLevelService;
import service.StudentService;
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
    private TextField txtFirstNameFilter;


    @FXML
    private TextField txtLastNameFilter;
    @FXML
    private AnchorPane studentTeacherPane;
    @FXML
    private TextField teacherID;

    @FXML
    private ComboBox<String> comboBoxLevel;

    @FXML
    private Pagination pagination;
    private final static int rowsPerPage = 15;

    private ObservableList<Teacher> dataList;
    @FXML
    void handleAddTeacherStudentClick(ActionEvent event) {
        Teacher selectedItem = TeachersTable.getSelectionModel().getSelectedItem();
        int teacher_id= selectedItem.getId();
        int school_id=selectedItem.getSchool_id();
        int level_id= returnId(comboBoxLevel.getValue());
        StudentTeacherDto userData= new StudentTeacherDto(teacher_id, school_id, level_id);
    }

    private void addSelection(){
        TeachersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Teacher selected: " + newSelection.getId()); // Debugging line
                teacherID.setText(String.valueOf(newSelection.getId()));
            } else {
                System.out.println("Selection is null"); // Debugging line
            }
        });
    }


    @FXML
    void handleTeacherStudentClick(ActionEvent event) {
        studentTeacherPane.setVisible(true);

    }

    private TableView<Teacher> createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
        TeachersTable.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
        return TeachersTable;
    }


    @FXML
    void handleFilterClick(ActionEvent event) {
        TeacherFilter filter = new TeacherFilter();
        filter.setFirstName(this.txtFirstNameFilter.getText());
        filter.setLastName(this.txtLastNameFilter.getText());
        ArrayList<Teacher> filterTeacher = TeacherService.filterTeacher(filter);
        if (filterTeacher == null) {
            System.out.println("Error occurred, check filter code!");
        } else {
            this.updateTable(filterTeacher);
        }
    }

        private void updateTable(ArrayList<Teacher> filterTeacher) {
            ObservableList<Teacher> filteredData = FXCollections.observableArrayList(filterTeacher);

            TeachersTable.setItems(filteredData);
        }


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

            dataList = FXCollections.observableArrayList(TeacherRepository.getAllTeachers());
            columnAddress.setCellValueFactory(new PropertyValueFactory<>("address_id"));
            columnBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
            columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnSubject.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
            columnSchool.setCellValueFactory(new PropertyValueFactory<>("school_id"));
            columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

            pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));
            pagination.setPageFactory(this::createPage);
            this.addSelection();



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


        ArrayList<String> levels= new ArrayList<>();
        for(Grade_level level: GradeLevelService.getAllLevels()){
            levels.add(level.getLevel_id()+" "+level.getLevel_name());
        }
        comboBoxLevel.getItems().addAll(levels);

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
