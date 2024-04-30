package Database;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        if(connection.isValid(1000)){
            System.out.println("Lidhja me baze te te dhenave u krijuar me sukses!");
        }
    }

}
