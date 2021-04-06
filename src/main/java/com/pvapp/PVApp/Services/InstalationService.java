package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.*;
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

    @Autowired
    ConstructionService constructionService;

    @Autowired
    InverterService inverterService;


    public List<Instalation> getAll() {
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }

    public void save(Instalation instalation) {
        instalation.setPower(calcPower(instalation));
        instalation.setPrice(calcPrice(instalation));
        instalationDBRepo.create(instalation);
    }

    public double calcPrice(Instalation instalation) {
        int numberOfPVModules = instalation.getNumberofpvmodule();
        int numberOfInverters = instalation.getNumberofinverters();
        PVModule pvModule = moduleService.getPVModule(instalation.getPvModule().getId());
        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        double currency = 4.45;
        double modulePrice = currency * pvModule.getPower() * pvModule.getPrice();
        double price = numberOfPVModules * modulePrice + construction.getPrice() * numberOfPVModules + inverter.getPrice() * numberOfInverters;
        return price;
    }

    public void delete(int id) {
        instalationDBRepo.delete(id);
    }


    public Instalation getById(int id) {
        return instalationDBRepo.printbyid(id);
    }

    public void update(Instalation instalation) {
        instalation.setPower(calcPower(instalation));
        instalation.setPrice(calcPrice(instalation));
        instalationDBRepo.update(instalation);
    }

//    public void designInstalationByBills(QuestionForm questionForm) {
//        int cost = questionForm.getBill();
//        double electricityPrice = 0.54;
//        int consumption = (int) Math.round((cost / electricityPrice) * 12 * 1.2);
//        PVModule pvModule = moduleService.getPVModule(1);
//        int numberOfModules = (int) Math.round(consumption / pvModule.getPower());
//        Construction construction = constructionService.getConstruction(1);
//        Inverter inverter = inverterService.getInverter(1);
//        Instalation instalation = new Instalation(pvModule, numberOfModules, inverter, 1, construction);
//        instalation.setPrice(calcPrice(instalation));
//        instalation.setPower(calcPower(instalation));
//        instalationDBRepo.create(instalation);
//    }


    public void createInstalation(QuestionForm questionForm) {
        int yearconsumption = consumption(questionForm.getBill());
        PVModule pvModule = moduleService.getPVModule(1);
        int numberOfModules = Math.round(yearconsumption / pvModule.getPower());
        Inverter inverter = inverterService.getInverter(1);
        Construction construction = getconstructionString(questionForm);
        Instalation instalation = new Instalation(pvModule, numberOfModules, inverter, 1, construction);
        instalation.setPrice(calcPrice(instalation));
        instalation.setPower(calcPower(instalation));
        instalationDBRepo.create(instalation);
    }

//    private Construction getconstruction(QuestionForm questionForm) {
//        int rooftype = questionForm.getRoofType().getValue();
//        int roofmaterial = questionForm.getRoofMaterial().getValue();
//        return constructionService.getConstructionByRoofTypeMaterial(rooftype,roofmaterial);
//    }

    private Construction getconstructionString(QuestionForm questionForm) {
        String rooftype = questionForm.getRoofType().toString();
        String roofmaterial = questionForm.getRoofMaterial().toString();
        return constructionService.getConstructionByRoofTypeMaterial(rooftype,roofmaterial);
    }

    private int consumption(int bill) {
        double electricprice = 0.54;
        return (int) Math.round((bill / electricprice) * 12 * 1.2);
    }

    private int calcPower(Instalation instalation) {
        return instalation.getNumberofpvmodule() * moduleService.getPVModule(instalation.getPvModule().getId()).getPower();
    }

}
