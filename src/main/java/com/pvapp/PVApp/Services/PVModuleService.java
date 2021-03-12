package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Domain.PVModule;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PVModuleService {

    @Autowired
    PVModuleDBRepo pvModuleRepo;


    public List<PVModule> getAllModules() {
        return new ArrayList<PVModule>(pvModuleRepo.printAll());
    }

    public void saveModule(PVModule pvModule) {
        pvModuleRepo.create(pvModule);
    }

    public PVModule getPVModule(int id) {
        return pvModuleRepo.printbyid(id);
    }

    public void deleteModule(int id) {
        pvModuleRepo.delete(id);
    }

    public void updatemodule(PVModule pvModule) {
        pvModuleRepo.update(pvModule.getId(), pvModule);
    }
}
