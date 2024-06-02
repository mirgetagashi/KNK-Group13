package service;

import model.Address;
import model.Students;
import repository.AddressRepository;
import repository.StudentRepository;
import java.util.ArrayList;

public class AddressService {
    public static ArrayList<Address> getAllCities(){
        return AddressRepository.getAllCities();
    }
    public static Address getById(int id){
        return AddressRepository.getById(id);
    }
}
