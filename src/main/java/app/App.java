package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.PasswordHasher;


public class App extends Application {
    @Override
    public void start(Stage stage) {

        Navigator.navigate(stage , Navigator.ADMIN_STUDENT_PAGE);

    }
}
