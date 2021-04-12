package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Repositories.DBRepositories.OwnerRepoDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    OwnerRepoDB ownerRepoDB;

    public void createOwner(Owner owner) {
        log.info("Saving owner --service");
        ownerRepoDB.create(owner);
    }

    public void deleteOwner(int id) {
        log.info("Deleting owner by id --service");
        ownerRepoDB.delete(id);
    }

    public void updateOwner(Owner owner) {
        log.info("Updating owner --service");
        ownerRepoDB.update(owner);
    }

    public Owner getOwner(int id) {
        log.info("Getting owner by id --service");
        return ownerRepoDB.printbyid(id);
    }

    public List<Owner> getOwners() {
        log.info("Getting all owners --service");
        return new ArrayList<Owner>(ownerRepoDB.printAll());
    }
}
