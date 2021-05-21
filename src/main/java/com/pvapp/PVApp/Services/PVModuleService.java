package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class PVModuleService {

    @Autowired
    PVModuleDBRepo pvModuleRepo;

    @Autowired
    InstalationService instalationService;


    public List<PVModule> getAllModules() {
        log.info("Getting all modules from DB --service");
        return new LinkedList<>(pvModuleRepo.printAll());
    }

    public void saveModule(PVModule pvModule) {
        log.info("Creating module at DB --service");
        pvModuleRepo.create(pvModule);
    }

    public PVModule getPVModule(int id) {
        log.info("Getting module from DB --service");
        return pvModuleRepo.printbyid(id);
    }

    public void deleteModule(int id) {


        log.info("Deleting module from DB --service");
        pvModuleRepo.delete(id);
    }

    public void updatemodule(PVModule pvModule) {
        log.info("Update module from DB --service");
        pvModuleRepo.update(pvModule);
        List<Instalation> instalations = instalationService.getAllByPVModule(pvModule);
        if (!instalations.isEmpty()) {
            for (int i = 0; i < instalations.size(); i++) {
                instalationService.update(instalations.get(i));
            }
        }
    }
}
