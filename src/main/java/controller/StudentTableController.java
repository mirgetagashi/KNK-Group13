package controller;

import app.SessionManager.StudentSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Grades_Teacher_Subject;
import model.Students;
import repository.StudentTableRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable {

    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> finalGrade;
    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> firstPeriodGrade;
    @FXML
    private TableColumn<Grades_Teacher_Subject, String> profFirstName;
    @FXML
    private TableColumn<Grades_Teacher_Subject, String> profLastName;
    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> secondPeriodGrade;
    @FXML
    private TableView<Grades_Teacher_Subject> studentTable;
    @FXML
    private TableColumn<Grades_Teacher_Subject, String> subject;
    @FXML
    private TableColumn<Grades_Teacher_Subject, Integer> subjectId;
    @FXML
    private Pagination pagination;

    private final static int rowsPerPage = 15;
    private ObservableList<Grades_Teacher_Subject> dataList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataList = FXCollections.observableArrayList(StudentTableRepository.getStudentsTable(StudentSession.getStudent().getId()));

        subjectId.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        secondPeriodGrade.setCellValueFactory(new PropertyValueFactory<>("secondPeriodGrade"));
        firstPeriodGrade.setCellValueFactory(new PropertyValueFactory<>("firstPeriodGrade"));
        profFirstName.setCellValueFactory(new PropertyValueFactory<>("teacherFirstName"));
        profLastName.setCellValueFactory(new PropertyValueFactory<>("teacherLastName"));
        finalGrade.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));

        pagination.setPageCount((int) Math.ceil((double) dataList.size() / rowsPerPage));
        pagination.setPageFactory(this::createPage);
    }

    private TableView<Grades_Teacher_Subject> createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
        studentTable.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
        return studentTable;
    }
}
