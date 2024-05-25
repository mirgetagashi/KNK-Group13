package repository.Interface;


import model.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressInterface {
    List<Address> getAllCities() throws SQLException;
    Address getAddressByCity(String city) throws SQLException;
    Address getById(int address_id) throws SQLException;
}