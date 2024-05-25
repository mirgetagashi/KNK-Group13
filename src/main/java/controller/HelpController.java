package controller;

import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HelpController {

    @FXML
    private void goToChangePassword(MouseEvent event) {
        Navigator.navigate(event, Navigator.FORGET_PASSWORD);
    }

    @FXML
    private void goToProfile(MouseEvent event) {
        Navigator.navigate(event, Navigator.STUDENT_PROFILE);
    }

    @FXML
    private void goToStatictics(MouseEvent event) {
        Navigator.navigate(event, Navigator.STUDENT_CHART);

    }

    // Add other event handler methods as needed
}
