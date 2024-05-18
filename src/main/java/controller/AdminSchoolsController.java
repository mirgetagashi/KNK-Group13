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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Address;
import model.Major;
import model.SchoolTable;
import model.Students;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import repository.*;
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
    private ComboBox<String> ComboBoxMajors;

    @FXML
    private AnchorPane invisiblePane;
    @FXML
    private HBox hBox;


    @FXML
    void handleAddMajorToSchoolClick(ActionEvent event) {
        invisiblePane.setVisible(true);
    }

    @FXML
    void handleAddMajorsClick(ActionEvent event) {
        SchoolTable selectedItem = tblSchools.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String selectedCity = ComboBoxMajors.getValue();
            String[] vargu = selectedCity.split(" ");
            int id=Integer.parseInt(vargu[0]);
            AddSchoolMajorDto userData= new AddSchoolMajorDto(selectedItem.getId(),id);
            boolean add = SchoolService.addSchoolMajor(userData);

            if (add) {
                Navigator.navigate( hBox, Navigator.ADMIN_SCHOOLS_PAGE);
            } else {
                 System.out.println("Gabim");
            }
        }
    }

    @FXML
    void handleAddClick(ActionEvent event) {
            int id=returnId(comboBoxCity.getValue());
        CreateSchoolDto userData= new CreateSchoolDto(
                this.txtName.getText(),
                id
        );
        boolean isAdded;
        isAdded= SchoolService.add(userData);
        if(isAdded){
            Navigator.navigate(event,Navigator.ADMIN_SCHOOLS_PAGE);
        }

    }


    @FXML
    void handleUpdateClick(ActionEvent event) {
    }

    @FXML
    void handleDeleteClick(ActionEvent event) {
        SchoolTable selectedItem = tblSchools.getSelectionModel().getSelectedItem();


        if (selectedItem != null) {

            boolean deleted = SchoolRepository.delete(selectedItem.getId());


            if (deleted) {
                tblSchools.getItems().remove(selectedItem);
            } else {
                System.out.println("Please select a row to delete.");
            }
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
        ComboBoxMajors.setValue("Majors");
        ArrayList<String> majors= new ArrayList<>();
        for (Major major : MajorRepository.getAllMajors()) {
            majors.add(major.getId()+" "+major.getMajor_name());
        }
        ComboBoxMajors.getItems().addAll(majors);

        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressRepository.getAllCities())) {
            cities.add(city.getId()+" "+city.getCity());
        }
        comboBoxCity.getItems().addAll(cities);

    }


    private int returnId(String select){
        String[] vargu=select.split(" ");
        int id=Integer.parseInt(vargu[0]);
        return id;
    }
}
