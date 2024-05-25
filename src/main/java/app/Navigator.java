package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String ADMIN_STUDENT_PAGE="admin_students_tabel.fxml";
    public  final static String ADMIN_PAGE="admin_navigator.fxml";
    public  final static String STUDENT_PAGE="student_navigator.fxml";
    public  final static String TEACHER_PAGE="teacher_navigator.fxml";
    public  final static String TEACHER_TABLE="teacherTable.fxml";
    public  final static String ADMIN_TEACHER_PAGE="admin_teachers_table.fxml";
    public  final static String ADMIN_SCHOOLS_PAGE="admin_schools_table.fxml";
    public  final static String TEACHER_DASHBOARD="teacherDashboard.fxml";



    public  final static String STUDENT_TABLE="student_table.fxml";
    public  final static String STUDENT_CHART="student_chart.fxml";
    public  final static String FORGET_PASSWORD="change_password.fxml";
    public  final static String STUDENT_SETTINGS="settings_student.fxml";

    public  final static String STUDENT_PROFILE="student_profile.fxml";
    public  final static String ADMIN_DASHBOARD="admin_dashboard.fxml";
    public final static String PROVA="prova.fxml";




    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }



    public static void navigate(Pane pane, String form){
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    private static Pane loadPane(String form){
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(form)
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
        }
    }


}