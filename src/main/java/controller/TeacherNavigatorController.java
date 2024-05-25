package controller;
import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TeacherNavigatorController {
    @FXML
    private HBox root;

    @FXML
    private AnchorPane dashBo;



    @FXML
    void dashboard(MouseEvent event) {
        Navigator.navigate(dashBo,Navigator.TEACHER_DASHBOARD);

    }
    @FXML
    void addgrade(MouseEvent event) {
        Navigator.navigate(dashBo,Navigator.TEACHER_TABLE);


    }

    @FXML
    void help(MouseEvent event) {

        Navigator.navigate(dashBo,Navigator.HELP_PAGE);
    }

    @FXML
    void profile(MouseEvent event) {

    }


}
