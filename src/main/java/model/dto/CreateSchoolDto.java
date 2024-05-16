package model.dto;

public class CreateSchoolDto {
    private String name;
    private int address_id;


    public CreateSchoolDto(String name, int address_id) {
        this.name = name;
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public int getAddress_id() {
        return address_id;
    }
}
