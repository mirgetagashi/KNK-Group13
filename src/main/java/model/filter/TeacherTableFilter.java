package model.filter;

import java.util.ArrayList;

public class TeacherTableFilter extends Filter{
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String buildQuery() {
        StringBuilder query = new StringBuilder();
        if (this.studentId != null) {
            query.append(" AND std_id = ? ");
        }
        return query.toString();
    }

    public ArrayList<Object> getFilterParams() {
        ArrayList<Object> params = new ArrayList<>();
        if (studentId != null) {
            params.add(studentId);
        }
        return params;
    }
}
