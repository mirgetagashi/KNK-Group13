package service.Interface;

import model.Address;

import java.util.ArrayList;

public interface AddressInterface {
    ArrayList<Address> getAllCities();
    Address getById(int id);

}
