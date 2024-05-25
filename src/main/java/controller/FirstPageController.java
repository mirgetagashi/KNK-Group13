package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import app.Navigator;

import java.util.Locale;

public class FirstPageController {

    @FXML
    private Button btnAlbanian;

    @FXML
    private Button btnEnglish;

    @FXML
    private Button btnFrench;

    @FXML
    void handleAlbanianLanguage(ActionEvent event) {
        Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(new Locale("sq"));
            Navigator.navigate(event, Navigator.LOGIN_PAGE);
        }


    @FXML
    void handleEnglishLanguage(ActionEvent event) {
        Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(new Locale("en"));
            Navigator.navigate(event, Navigator.LOGIN_PAGE);
    }

    @FXML
    void handleFrenchLanguage(ActionEvent event) {
        Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(new Locale("fr"));
            Navigator.navigate(event, Navigator.LOGIN_PAGE);
    }

    @FXML
    void handleItalianLanguage(ActionEvent event) {
        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(new Locale("it"));
        Navigator.navigate(event, Navigator.LOGIN_PAGE);
    }
}
