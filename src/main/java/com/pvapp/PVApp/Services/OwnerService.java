package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Repositories.DBRepositories.OwnerRepoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    @Autowired
    OwnerRepoDB ownerRepoDB;

    public void createOwner(Owner owner) {
        ownerRepoDB.create(owner);
    }

    public void deleteOwner(int id) {
        ownerRepoDB.delete(id);
    }

    public void updateOwner(Owner owner) {
        ownerRepoDB.update(owner);
    }

    public Owner getOwner(int id) {
        return ownerRepoDB.printbyid(id);
    }

    public List<Owner> getOwners() {
        return new ArrayList<Owner>(ownerRepoDB.printAll());
    }
}
