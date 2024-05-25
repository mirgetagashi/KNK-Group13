package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String URL = "jdbc:mysql://localhost:3306/project_knk";
    private static String USER = "root";
    private static String PASSWORD = "root";

 
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
        if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(
                        URL, USER, PASSWORD
                );
        }
        return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
