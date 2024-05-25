package model.filter;
import java.util.ArrayList;



public class SchoolFilter extends Filter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String buildQuery() {

        StringBuilder  query=new StringBuilder();
        if(this.name!=null && !this.name.isEmpty()){
            query.append(" AND school_name like ? ");
        }
        return query.toString();
    }

    @Override
    public ArrayList<Object> getFilterParams() {
        ArrayList<Object> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            params.add(name+"%");
        }
        return params;
    }


}


