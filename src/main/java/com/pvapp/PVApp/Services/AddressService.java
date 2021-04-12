package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Address;
import com.pvapp.PVApp.Repositories.DBRepositories.AddressRepoDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressService {

    @Autowired
    AddressRepoDB addressRepoDB;

    public void createAddress(Address address) {
        log.info("Creating address --repository");
        addressRepoDB.create(address);
    }

    public void deleteAddress(int id) {
        log.info("Deleting address by id --repository: " + id);
        addressRepoDB.delete(id);
    }

    public void updateAddress(Address address) {
        log.info("Updating address --repository");
        addressRepoDB.update(address);
    }

    public Address getAddress(int id) {
        log.info("Getting address by id --repository: " + id);
        return addressRepoDB.printbyid(id);
    }

    public List<Address> getAddresses() {
        log.info("Getting all addresses --repository");
        return new ArrayList<Address>(addressRepoDB.printAll());
    }

}
