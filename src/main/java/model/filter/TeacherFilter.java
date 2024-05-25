package model.filter;

import java.util.ArrayList;

public class TeacherFilter extends Filter{
    private String firstName;
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String buildQuery() {

        StringBuilder  query=new StringBuilder();
        if(this.firstName!=null && !this.firstName.isEmpty()){
            query.append(" AND t_name like ? ");
        }
        if(this.lastName!=null && !this.lastName.isEmpty()){
            query.append(" AND t_lastName like ? ");
        }

        return query.toString();
    }

    @Override
    public ArrayList<Object> getFilterParams() {
        ArrayList<Object> params = new ArrayList<>();
        if (firstName != null && !firstName.isEmpty()) {
            params.add(firstName+"%");
        }
        if (lastName != null && !lastName.isEmpty()) {
            params.add(lastName+"%");
        }

        return params;
    }
}
