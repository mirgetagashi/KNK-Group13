package model.filter;

import java.util.ArrayList;

public class SchoolFilter extends Filter {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String buildQuery() {
        StringBuilder query = new StringBuilder();
        if (this.name != null && !this.name.isEmpty()) {
            query.append(" AND school_name like ? ");
        }
        if (this.city != null && !this.city.isEmpty()) {
            query.append(" AND city like ? ");
        }
        return query.toString();
    }

    @Override
    public ArrayList<Object> getFilterParams() {
        ArrayList<Object> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            params.add(name + "%");
        }
        if (city != null && !city.isEmpty()) {
            params.add(city + "%");
        }
        return params;
    }
}
