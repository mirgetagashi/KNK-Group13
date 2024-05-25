package service.Interface;

import model.Students;
import model.dto.*;
import model.filter.StudentFilter;
import repository.GradeRepository;
import repository.StudentRepository;
import service.PasswordHasher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentInterface {
    boolean signUp(StudentDto userData);

    boolean login(LoginUserDto loginData);
    ArrayList<Students> filterStudents(StudentFilter filter);

    ArrayList<Students> filter(StudentFilter filter) throws SQLException ;

    boolean update(UpdateStudentDto userData);

    Students getById(int id);


    boolean changePassword(ChangePasswordRequestDto userData);
    List<Integer> getGradesByStudent(int std_id);
    ArrayList<Students> getAllStudents();

    boolean delete(int id);
    Students getByEmail(String email);


}
