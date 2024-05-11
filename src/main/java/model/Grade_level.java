package model;

public class Grade_level {
    private int level_id;
    private String level_name;

    public Grade_level(int level_id, String level_name) {
        this.level_id = level_id;
        this.level_name = level_name;
    }

    public int getLevel_id() {
        return level_id;
    }

    public String getLevel_name() {
        return level_name;
    }
}
