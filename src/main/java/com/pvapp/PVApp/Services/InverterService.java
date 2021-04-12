package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InverterService {

    @Autowired
    InverterDBRepo inverterRepo;


    public List<Inverter> getAllInverters() {
        log.info("Getting all inverters --service");
        return new ArrayList<Inverter>(inverterRepo.printAll());
    }

    public void saveInverter(Inverter inverter) {
        log.info("Saving inverter --service");
        inverterRepo.create(inverter);
    }

    public void deleteInverter(int id) {
        log.info("Deleting inverter --service");
        inverterRepo.delete(id);
    }

    public Inverter getInverter(int id) {
        log.info("Getting inverter by id --service");
        return inverterRepo.printbyid(id);
    }

    public void update(Inverter inverter) {
        log.info("Updating inverter --service");
         inverterRepo.update(inverter);
    }

}
