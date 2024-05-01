package Database;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

            Connection connection = DatabaseUtil.getConnection();
            if(connection.isValid(1000)){
                System.out.println("Lidhja me baze te te dhenave u krijuar me sukses!");
            }

    }
    public static void lexoPerdoruesin(String id) throws SQLException{
        String sql = "SELECT * FROM users WHERE id = ?";
        System.out.println(sql);
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                sql
        );
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            User user = User.getInstanceFromResultSet(result);
            user.printoDetajet();
        }
    }
}







class User{
    private int id;
    private String username;
    private String userType;

    private User(int id, String username, String userType){
        this.id = id;
        this.username = username;
        this.userType=userType;
    }

    public static User getInstanceFromResultSet(ResultSet resultSet){
        try{
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String userType = resultSet.getString("user_type");
            return new User(id, username,userType);
        }catch (Exception e){
            return null;
        }
    }

    public void printoDetajet(){
        System.out.println("Id: " + this.id);
        System.out.println("Username: " + this.username);
        System.out.println("User type: "+this.userType);
    }
}
