package model.filter;

import repository.GradeLevelRepository;
import repository.MajorRepository;
import repository.SchoolRepository;

import java.util.ArrayList;

public class StudentFilter extends Filter{
    private String name;
    private String school;
    private String level;

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getLevel() {
        return level;
    }


    @Override
    public String buildQuery() {
        StringBuilder  query=new StringBuilder();
        if(this.name!=null && !name.isEmpty()){
            query.append(" AND std_name like ? ");
        }
        if(!this.school.equals("School")){
            int id=returnId(this.school);
            query.append(" AND school_id = ?");
        }

        if(!this.level.equals("Level")){
            int id=returnId(this.level);
            query.append(" AND level_id = ?");
        }

        return query.toString();
    }


    @Override
    public ArrayList<Object> getFilterParams() {
        ArrayList<Object> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            params.add(name+"%");
        }
        if(!this.school.equals("School")){
            params.add(returnId(school));
        }

        if(!this.level.equals("Level")){
            params.add(returnId(level));
        }
        return params;
    }

    private int returnId(String select){
        String[] vargu=select.split(" ");
        int id=Integer.parseInt(vargu[0]);
        return id;
    }
}
