package service;

import repository.TeacherDashboardRepository;
import service.Interface.TeacherDashboardInterface;

import java.util.List;

public class TeacherDashboardService implements TeacherDashboardInterface {


    public double calculateAverageFinalGradeByTeacherId(int teacherId) {
        return TeacherDashboardRepository.calculateAverageFinalGradeByTeacherId(teacherId);
    }
    public List<Integer> getGradesByTeacherId(int teacherId){
        return TeacherDashboardRepository.getGradesByTeacherId(teacherId);
    }
}

