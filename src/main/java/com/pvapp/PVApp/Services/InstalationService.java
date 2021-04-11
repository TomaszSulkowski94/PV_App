package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.*;
import com.pvapp.PVApp.Repositories.DBRepositories.InstalationDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    @Autowired
    ProductionService productionService;

    @Autowired
    QuestionFormService questionFormService;


    public List<Instalation> getAll() {
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }

    public void save(Instalation instalation) {
        log.info("Saving instalation --service");
        instalation.setPower(calcPower(instalation.getNumberofpvmodule(), moduleService.getPVModule(instalation.getPvModule().getId())));
        instalation.setPrice(calcPrice(instalation));
        QuestionForm questionForm = new QuestionForm(0, null, null, null, null);
        questionFormService.create(questionForm);
        instalation.setQuestionForm(questionForm);
        Production production = productionService.createProduction(instalation);
        instalation.setProduction(production);
        instalationDBRepo.create(instalation);
    }

    public void update(Instalation instalation) {
        log.info("Updating instalation --service");
        instalation.setPower(calcPower(instalation.getNumberofpvmodule(), instalation.getPvModule()));
        instalation.setPrice(calcPrice(instalation));
        productionService.updateProduction(instalation);
        instalationDBRepo.update(instalation);
    }


    public void createInstalation(QuestionForm questionForm) {
        log.info("Saving instalation to DB");
        int yearconsumption = consumption(questionForm.getBill());
        PVModule pvModule = moduleService.getPVModule(1);
        int numberOfModules = Math.round(yearconsumption / pvModule.getPower());
        double instalationSize = calcPower(numberOfModules, pvModule);
        Inverter inverter = getInverterByPower(instalationSize);
        Construction construction = getconstructionString(questionForm);
        Instalation instalation = new Instalation(pvModule, numberOfModules, inverter, 1, construction);
        instalation.setPower(instalationSize);
        instalation.setPrice(calcPrice(instalation));
        instalation.setQuestionForm(questionForm);
        Production production = productionService.createProduction(instalation);
        instalation.setProduction(production);
        instalationDBRepo.create(instalation);
    }

    public double calcPrice(Instalation instalation) {
        log.info("Calculating price --service");
        int numberOfPVModules = instalation.getNumberofpvmodule();
        int numberOfInverters = instalation.getNumberofinverters();
        PVModule pvModule = moduleService.getPVModule(instalation.getPvModule().getId());
        Inverter inverter = instalation.getInverter();
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        double currency = 4.45;
        double modulePrice = currency * pvModule.getPower() * pvModule.getPrice();
        double price = numberOfPVModules * modulePrice + construction.getPrice() * numberOfPVModules + inverter.getPrice() * numberOfInverters;
        price = (Math.round(price * 100)) / 100;
        return price;
    }


    private Construction getconstructionString(QuestionForm questionForm) {
        String rooftype = questionForm.getRooftype().toString();
        String roofmaterial = questionForm.getRoofmaterial().toString();
        log.info("Getting construcion by rooftype:" + rooftype + " and roofmaterial:" + roofmaterial);
        return constructionService.getConstructionByRoofTypeMaterial(rooftype, roofmaterial);
    }

    private int consumption(int bill) {
        log.info("Calculationg consumption");
        double electricprice = 0.54;
        return (int) Math.round((bill / electricprice) * 12 * 1.2);
    }

    private Inverter getInverterByPower(double power) {
        List<Inverter> inverterList = inverterService.getAllInverters();
        for (int i = 0; i < inverterList.size(); i++) {
            if (power > inverterList.get(i).getAcpower() && power <= inverterList.get(i).getDcpower()) {
                return inverterList.get(i);
            }
        }
        return inverterList.get(inverterList.size());
    }

    private int calcPower(int numberOfPVModules, PVModule pvModule) {
        return numberOfPVModules * pvModule.getPower();
    }

    public void delete(int id) {
        log.info("Removing instalation from DB --service");
        instalationDBRepo.delete(id);
    }


    public Instalation getById(int id) {
        log.info("Getting instalation from DB by id --service");
        return instalationDBRepo.printbyid(id);
    }
}
