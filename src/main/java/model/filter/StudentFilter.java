package model.filter;

import repository.GradeLevelRepository;
import repository.MajorRepository;
import repository.SchoolRepository;

public class StudentFilter extends Filter{
    private String name;
    private String school;
    private String major;
    private String level;

    private int page;
    private int size;

    public StudentFilter(String name, String school, String major, String level, int page, int size) {
        this.name = name;
        this.school = school;
        this.major = major;
        this.level = level;
        this.page = page;
        this.size = size;
    }

    @Override
    public String buildQuery() {
        StringBuilder  query=new StringBuilder();
        if(this.name!=null){
            query.append(" AND std_name like "+ this.name+"%");
        }
        if(this.school!=null){
            int id= SchoolRepository.getSchoolByName(this.school).getId();
            query.append(" AND school_id = "+ id);
        }
        if(this.major!=null){
            int id= MajorRepository.getMajorByName(this.major).getId();
            query.append(" AND major_id = "+id);
        }
        if(this.level!=null){
            int id= GradeLevelRepository.getLevelByName(this.level).getLevel_id();
            query.append(" AND level_id = "+id);
        }


        return query.toString();
    }
}
