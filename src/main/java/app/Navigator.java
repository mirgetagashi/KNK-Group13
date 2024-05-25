package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String ADMIN_STUDENT_PAGE = "admin_students_tabel.fxml";
    public final static String ADMIN_PAGE = "admin_navigator.fxml";
    public final static String STUDENT_PAGE = "student_navigator.fxml";
    public final static String TEACHER_PAGE = "teacher_navigator.fxml";
    public final static String TEACHER_TABLE = "teacherTable.fxml";
    public final static String ADMIN_TEACHER_PAGE = "admin_teachers_table.fxml";
    public final static String ADMIN_SCHOOLS_PAGE = "admin_schools_table.fxml";
    public final static String TEACHER_DASHBOARD = "teacherDashboard.fxml";
    public final static String HELP_PAGE = "help.fxml";
    public final static String STUDENT_NAVIGATOR = "student_navigator.fxml";
    public final static String STUDENT_TABLE = "student_table.fxml";
    public final static String STUDENT_CHART = "student_chart.fxml";
    public final static String FORGET_PASSWORD = "change_password.fxml";
    public final static String STUDENT_SETTINGS = "settings_student.fxml";
    public final static String STUDENT_PROFILE = "student_profile.fxml";
    public final static String ADMIN_DASHBOARD = "admin_dashboard.fxml";
    public final static String FIRST_PAGE = "first_page.fxml";
    public final static String TEACHER_NAVIGATOR = "teacherNavigator.fxml";
    public final static String TEACHER_PROFILE = "teacherProfile.fxml";
    public final static String PROVA = "prova.fxml";


    public static void navigate(Event event, String form) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(javafx.scene.input.MouseEvent event, String form) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form) {
        Pane formPane = loadPane(form, Locale.getDefault());
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }

    public static void navigate(Pane pane, String form) {
        Pane formPane = loadPane(form, Locale.getDefault());
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

//    private void changeLanguage(){
//        Locale locale = Locale.of("sq");
//        Locale.setDefault(locale);
//    }

    private static Pane loadPane(String form, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", locale);
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form), bundle);
        try {
            return loader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }




}