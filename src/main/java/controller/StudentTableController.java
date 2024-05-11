package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentTableController {
    @FXML
    private TableColumn<?, ?> finalGrade;

    @FXML
    private TableColumn<?, ?> firstPeriodGrade;

    @FXML
    private TableColumn<?, ?> no;

    @FXML
    private TableColumn<?, ?> professor;

    @FXML
    private TableColumn<?, ?> secondPeriodGrade;

    @FXML
    private TableView<?> studentTable;

    @FXML
    private TableColumn<?, ?> subject;

    @FXML
    private TableColumn<?, ?> subjectId;
}
