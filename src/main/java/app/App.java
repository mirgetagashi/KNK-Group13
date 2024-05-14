package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.PasswordHasher;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {

<<<<<<< Updated upstream
        Navigator.navigate(stage, Navigator.REGISTER_PAGE);
=======
        Navigator.navigate(stage, Navigator.LOGIN_PAGE);

>>>>>>> Stashed changes
    }
}
