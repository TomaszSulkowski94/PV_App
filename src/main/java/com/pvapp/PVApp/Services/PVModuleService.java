package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Domain.PVModule;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PVModuleService {

    @Autowired
    PVModuleDBRepo pvModuleRepo;

    public List<PVModule> getAllModules() {
        log.info("Printing all modules from DB");
        return new ArrayList<PVModule>(pvModuleRepo.printAll());
    }

    public void saveModule(PVModule pvModule) {
        log.info("Creating module at DB");
        pvModuleRepo.create(pvModule);
    }

    public PVModule getPVModule(int id) {
        log.info("Print module from DB");
        return pvModuleRepo.printbyid(id);
    }

    public void deleteModule(int id) {
        log.info("Delete module from DB");
        pvModuleRepo.delete(id);
    }

    public void updatemodule(PVModule pvModule) {
        log.info("Update module from DB");
        pvModuleRepo.update(pvModule);
    }
}
