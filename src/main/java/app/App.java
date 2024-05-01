package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader design = new FXMLLoader(
                App.class.getResource("login_form.fxml")
        );
        Scene scene = new Scene(design.load());
        stage.setScene(scene);
        stage.show();
    }
}
