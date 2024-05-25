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
import javafx.scene.layout.HBox;
import model.*;
import model.dto.AddSchoolMajorDto;
import model.dto.CreateSchoolDto;
import model.filter.SchoolFilter;
import repository.*;
import service.AddressService;
import service.MajorService;
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
    private Pagination pagination;
    private final static int rowsPerPage = 15;

    private ObservableList<SchoolTable> dataList;
    @FXML
    private TextField txtNameFilter;

    @FXML
    void handleFilterClick(ActionEvent event) {
        SchoolFilter filter = new SchoolFilter();
        filter.setName(txtNameFilter.getText());
        ArrayList<SchoolTable> filterSchool = SchoolService.filterSchool(filter);
        if (filterSchool == null) {
            System.out.println("Error occurred, check filter code!");
        } else {
            this.updateTable(filterSchool);
        }

    }


    private void updateTable(ArrayList<SchoolTable> filterSchool) {
        ObservableList<SchoolTable> filteredData = FXCollections.observableArrayList(filterSchool);

        tblSchools.setItems(filteredData);
    }



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

            boolean deleted = SchoolService.delete(selectedItem.getId());


            if (deleted) {
                tblSchools.getItems().remove(selectedItem);
            } else {
                System.out.println("Please select a row to delete.");
            }
        }
    }

    private TableView<SchoolTable> createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
        tblSchools.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
        return tblSchools;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        columnNumOfStudents.setCellValueFactory(new PropertyValueFactory<>("numberOfStudents"));
        columnNumberOfMajors.setCellValueFactory(new PropertyValueFactory<>("numberOfMajors"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        dataList = FXCollections.observableArrayList(FXCollections.observableArrayList(AdminDashboardRepository.getSchoolsInfo()));

        pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));

        pagination.setPageFactory(this::createPage);


        comboBoxCity.setValue("Address");
        ComboBoxMajors.setValue("Majors");
        ArrayList<String> majors= new ArrayList<>();
        for (Major major : MajorService.getAllMajors()) {
            majors.add(major.getId()+" "+major.getMajor_name());
        }
        ComboBoxMajors.getItems().addAll(majors);

        ArrayList<String> cities= new ArrayList<>();
        for (Address city : (AddressService.getAllCities())) {
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
