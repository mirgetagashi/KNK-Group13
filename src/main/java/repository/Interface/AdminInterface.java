package repository.Interface;

import model.Administrator;

import java.sql.SQLException;

public interface AdminInterface {
    Administrator getByEmail(String email) throws SQLException;
    Administrator getById(int std_id) throws SQLException;
}
