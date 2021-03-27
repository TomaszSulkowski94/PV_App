package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
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
    InverterService inverterService;

    @Autowired
    ConstructionService constructionService;

    @Autowired
    PVModuleService pvModuleService;

    public List<Instalation> getAll() {
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }

    public void save(Instalation instalation) {
        System.out.println("wchodzę w serwis save 1");
        instalationDBRepo.create(instalation);
    }

    public void delete(int id) {
        instalationDBRepo.delete(id);
    }

    public void save(Instalation instalation, int moduleId, int inverterId, int constructionId) {
        System.out.println("wchodze w serwis save 2");
        instalation.setPvModule(pvModuleService.getPVModule(moduleId));
        instalation.setInverter(inverterService.getInverter(inverterId));
        instalation.setConstruction(constructionService.getConstruction(constructionId));
        int power = instalation.getNumberofpvmodule() * instalation.getPvModule().getPower();
        instalation.setPower(power);
        System.out.println("Id instalacji " + instalation.getId());
        System.out.println("Moduł " + instalation.getPvModule().getId());
        System.out.println("Liczba modułów " + instalation.getNumberofpvmodule());
        System.out.println("Konstrukcja " + instalation.getConstruction().getId());
        System.out.println("Inverter " + instalation.getInverter().getId());
        System.out.println("Liczba falownikow " + instalation.getNumberofinverters());
        instalationDBRepo.create(instalation);
    }
}
