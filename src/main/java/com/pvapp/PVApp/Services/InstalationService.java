package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.DBRepositories.InstalationDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstalationService {


    @Autowired
    InstalationDBRepo instalationDBRepo;

    @Autowired
    PVModuleService moduleService;


    public List<Instalation> getAll() {
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }

    public void save(Instalation instalation) {
        int power = instalation.getNumberofpvmodule() * moduleService.getPVModule(instalation.getPvModule().getId()).getPower();
        instalation.setPower(power);
        instalationDBRepo.create(instalation);
    }

    public void delete(int id) {
        instalationDBRepo.delete(id);
    }


    public Instalation getById(int id) {
        return instalationDBRepo.printbyid(id);
    }

    public void update(Instalation instalation) {
        int power = instalation.getNumberofpvmodule() * moduleService.getPVModule(instalation.getPvModule().getId()).getPower();
        instalation.setPower(power);
        instalationDBRepo.update(instalation);
    }
}
