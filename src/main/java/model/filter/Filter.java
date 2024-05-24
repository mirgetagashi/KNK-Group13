package model.filter;

import java.util.ArrayList;

abstract class Filter {
    public abstract String buildQuery();
    public abstract ArrayList<Object> getFilterParams();
}
