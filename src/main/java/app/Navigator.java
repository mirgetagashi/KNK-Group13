package app;

import controller.NextStudentController;
import controller.NextTeacherController;
import controller.TeacherSignUpController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dto.UserDto;

import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String SIGN_UP_PAGE = "sign_up_form.fxml";
    public  final static String NEXT_STUDENT="next_student.fxml";
    public final static String REGISTER_PAGE="register.fxml";
    public final static String ADMIN_STUDENT_PAGE="admin_students_tabel.fxml";
    public  final static String NEXT_TEACHER="next_teacher.fxml";
    public  final static String ADMIN_PAGE="admin_navigator.fxml";
    public  final static String STUDENT_PAGE="student_navigator.fxml";
    public  final static String TEACHER_PAGE="teacher_navigator.fxml";
    public  final static String TEACHER_TABLE="teacherTable.fxml";
    public  final static String ADMIN_TEACHER_PAGE="admin_teachers_table.fxml";
    public  final static String STUDENT_TABLE="student_table.fxml";
    public  final static String STUDENT_CHART="student_chart.fxml";
    public  final static String FORGET_PASSWORD="Forget_Password.fxml";
    public  final static String STUDENT_SETTINGS="settings_student.fxml";

    public  final static String STUDENT_PROFILE="student_profile.fxml";




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


    public static void navigateWithDto(Pane pane, String form, UserDto userDto){
        Pane formPane = loadPaneWithDto(form, userDto);
        if(formPane != null){
            pane.getChildren().clear();
            pane.getChildren().add(formPane);
        }
    }

    private static Pane loadPaneWithDto(String form, UserDto userDto){
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form));
            Pane formPane = loader.load();

            if(userDto.getEmail().contains("@student")){
                NextStudentController controller = loader.getController();
                controller.setUserDto(userDto);
            } else {
                NextTeacherController controller = loader.getController();
                controller.setUserDto(userDto);
            }

            return formPane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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