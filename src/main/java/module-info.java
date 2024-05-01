module com.example.knkgroup13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports app;
    opens controller to javafx.fxml;
    opens app to javafx.graphics;
    opens com.example.knkgroup13 to javafx.fxml;

}