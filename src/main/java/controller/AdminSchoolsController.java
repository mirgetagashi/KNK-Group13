package controller;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Address;
import model.SchoolTable;
import model.Students;
import model.dto.CreateSchoolDto;
import repository.AddressRepository;
import repository.AdminDashboardRepository;
import repository.SchoolRepository;
import repository.StudentRepository;
import service.SchoolService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminSchoolsController  implements Initializable {

    @FXML
    private TableColumn<SchoolTable, String> columnCity;

    @FXML
    private TableColumn<SchoolTable, Integer> columnID;

    @FXML
    private TableColumn<SchoolTable, String> columnName;

    @FXML
    private TableColumn<SchoolTable, Integer> columnNumOfStudents;

    @FXML
    private TableColumn<SchoolTable, Integer> columnNumberOfMajors;


    @FXML
    private TableView<SchoolTable> tblSchools;
    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private TextField txtName;

    @FXML
    void handleAddClick(ActionEvent event) {
        CreateSchoolDto userData= new CreateSchoolDto(
                this.txtName.getText(),
                AddressRepository.getAddressByCity(comboBoxCity.getValue()).getId()
        );
        boolean isAdded;
        isAdded= SchoolService.addSchool(userData);
        if(isAdded){
            Navigator.navigate(event,Navigator.ADMIN_SCHOOLS_PAGE);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<SchoolTable> schoolTables=FXCollections.observableArrayList(AdminDashboardRepository.getSchoolsInfo());
        if (tblSchools != null) {
            columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            columnNumOfStudents.setCellValueFactory(new PropertyValueFactory<>("numberOfStudents"));
            columnNumberOfMajors.setCellValueFactory(new PropertyValueFactory<>("numberOfMajors"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tblSchools.setItems(schoolTables);
        } else {
            System.out.println("StudentTable is null.");
        }

        comboBoxCity.setValue("Address");

        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getCity());
        }
        comboBoxCity.getItems().addAll(cities);

    }
}
