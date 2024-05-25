package service;

import model.Address;
import model.Students;
import repository.AddressRepository;
import repository.StudentRepository;
import service.Interface.AddressInterface;

import java.util.ArrayList;

public class AddressService implements AddressInterface {
    AddressRepository  AddressRepository= new AddressRepository();
    public ArrayList<Address> getAllCities(){
        return AddressRepository.getAllCities();
    }
    public Address getById(int id){
        return AddressRepository.getById(id);
    }
}
