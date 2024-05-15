package app.SessionManager;

import model.Administrator;
import model.Teacher;

public class AdminSession {
    private static Administrator admin;
    public static void setAdmin(Administrator newAdmin){
        admin = newAdmin;
    }
    public static Administrator getAdmin(){
        return admin;
    }
}
