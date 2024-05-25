package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage stage){

        Navigator.navigate(stage, Navigator.ADMIN_SCHOOLS_PAGE);

    }

}