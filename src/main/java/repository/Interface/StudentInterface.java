package repository.Interface;

import model.Students;
import model.dto.ChangePasswordDto;
import model.dto.CreateStudentDto;
import model.dto.UpdateStudentDto;
import model.filter.StudentFilter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentInterface {
    boolean create(CreateStudentDto userData) throws SQLException;
    Students getByEmail(String email) throws SQLException;
    Students getById(int std_id) throws SQLException;
    ArrayList<Students> getAllStudents() throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(UpdateStudentDto userData) throws SQLException;
    String getSaltById(int studentId) throws SQLException;
    boolean doesStudentExist(String email) throws SQLException;
    boolean updatePassword(ChangePasswordDto userData) throws SQLException;
    ArrayList<Students> getByFilter(StudentFilter filter) throws SQLException;
}