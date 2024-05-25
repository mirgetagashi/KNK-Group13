module com.example.knkgroup13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;
    requires java.desktop;

    exports app;
    opens controller to javafx.fxml;
    opens app to javafx.graphics;
   // opens model to javafx.graphics;
    opens com.example.knkgroup13 to javafx.fxml;
    opens model to javafx.base;
 //   opens model to javafx.base;

}