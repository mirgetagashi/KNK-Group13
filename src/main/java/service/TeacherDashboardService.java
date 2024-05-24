package service;

import repository.TeacherDashboardRepository;

import java.util.List;

public class TeacherDashboardService {

    public static double calculateAverageFinalGradeByTeacherId(int teacherId) {
        return TeacherDashboardRepository.calculateAverageFinalGradeByTeacherId(teacherId);
    }
    public static List<Integer> getGradesByTeacherId(int teacherId){
        return TeacherDashboardRepository.getGradesByTeacherId(teacherId);
    }
}

