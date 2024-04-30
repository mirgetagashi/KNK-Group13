module com.example.knkgroup13 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.knkgroup13 to javafx.fxml;
    exports com.example.knkgroup13;
}