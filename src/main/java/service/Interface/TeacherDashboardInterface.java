package service.Interface;

import repository.TeacherDashboardRepository;

import java.util.List;

public interface TeacherDashboardInterface {
    double calculateAverageFinalGradeByTeacherId(int teacherId);
    List<Integer> getGradesByTeacherId(int teacherId);

}
