package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminSchoolsController {

    @FXML
    private TableColumn<?, ?> columnCity;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnNumOfStudents;

    @FXML
    private TableView<?> tblSchools;

}
