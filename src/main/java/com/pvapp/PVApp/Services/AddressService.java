package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Address;
import com.pvapp.PVApp.Repositories.DBRepositories.AddressRepoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepoDB addressRepoDB;

    public void createAddress(Address address) {
        addressRepoDB.create(address);
    }

    public void deleteAddress(int id) {
        addressRepoDB.delete(id);
    }

    public void updateAddress(Address address) {
        addressRepoDB.update(address);
    }

    public Address getAddress(int id) {
        return addressRepoDB.printbyid(id);
    }

    public List<Address> getAddresses() {
        return new ArrayList<Address>(addressRepoDB.printAll());
    }

}
