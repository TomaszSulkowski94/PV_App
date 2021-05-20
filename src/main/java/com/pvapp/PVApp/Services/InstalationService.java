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

    @Autowired
    TechnicalResultService technicalResultService;

    @Autowired
    PriceService priceService;

    public List<Instalation> getAll() {
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }

    public List<Instalation> getAllByPVModule(PVModule pvModule) {
        return new ArrayList<Instalation>(instalationDBRepo.getByPVModule(pvModule));
    }

    public List<Instalation> getAllByConstruction(Construction construction) {
        return new ArrayList<Instalation>(instalationDBRepo.getByConstruction(construction));
    }

    public List<Instalation> getAllByInverter (Inverter inverter) {
        return new ArrayList<Instalation>(instalationDBRepo.getByInverter(inverter));
    }

    public void save(Instalation instalation) {
        log.info("Saving instalation --service");
        instalation.setPower(calcPower(instalation.getNumberofpvmodule(), moduleService.getPVModule(instalation.getPvModule().getId())));
        instalation.setInstalationangle(setInstalationAngle(instalation));
        QuestionForm questionForm = new QuestionForm(50, setRoofTypeFromInstalation(instalation), setInstalationAngle(instalation), instalation.getRoofposition(), setRoofMaterialFromInstalation(instalation), instalation);
        questionFormService.create(questionForm);
        instalation.setQuestionForm(questionForm);
        productionService.createProduction(instalation);
        technicalResultService.createTR(instalation);
        priceService.createPrice(instalation);
        instalationDBRepo.create(instalation);
    }

    public void createInstalation(QuestionForm questionForm) {
        log.info("Saving instalation to DB --service");
        int yearconsumption = consumption(questionForm.getBill());
        PVModule pvModule = moduleService.getPVModule(1);
        int numberOfModules = Math.round(yearconsumption / pvModule.getPower());
        double instalationSize = calcPower(numberOfModules, pvModule);
        Inverter inverter = getInverterByPower(instalationSize);
        Construction construction = getconstructionString(questionForm);
        Instalation instalation = new Instalation(pvModule, numberOfModules, inverter, 1, construction);
        instalation.setPower(instalationSize);
        instalation.setQuestionForm(questionForm);
        instalation.setInstalationangle(setInstalationAngleQF(questionForm, construction));
        instalation.setRoofposition(setRoofPosition(questionForm));
        productionService.createProduction(instalation);
        technicalResultService.createTR(instalation);
        priceService.createPrice(instalation);
        instalationDBRepo.create(instalation);
    }

    public void updateByQuestionForm(QuestionForm questionForm) {
        log.info("Updating instalation --service");
        Instalation instalation = instalationDBRepo.printbyid(questionFormService.getQuestionForm(questionForm.getId()).getId());
        int yearconsumption = consumption(questionForm.getBill());
        PVModule pvModule = instalation.getPvModule();
        int numberOfModules = Math.round(yearconsumption / pvModule.getPower());
        double instalationSize = calcPower(numberOfModules, pvModule);
        Inverter inverter = getInverterByPower(instalationSize);
        Construction construction = getconstructionString(questionForm);
        instalation.setRoofposition(setRoofPosition(questionForm));
        instalation.setNumberofpvmodule(numberOfModules);
        instalation.setPower(instalationSize);
        instalation.setInverter(inverter);
        instalation.setConstruction(construction);
        priceService.updatePrice(instalation);
        instalation.setInstalationangle(setInstalationAngleQF(questionForm, construction));
        technicalResultService.updateTR(instalation);
        productionService.updateProduction(instalation);
        instalationDBRepo.update(instalation);
    }

    public void update(Instalation instalation) {
        log.info("Updating instalation --service");
        double power = calcPower(instalation.getNumberofpvmodule(), moduleService.getPVModule(instalation.getPvModule().getId()));
        instalation.setPower(power);
        QuestionForm questionForm = questionFormService.getQuestionForm(instalation.getQuestionForm().getId());
        questionForm.setRoofmaterial(setRoofMaterialFromInstalation(instalation));
        questionForm.setRoofslope(setInstalationAngle(instalation));
        questionForm.setRoofposition(instalation.getRoofposition());
        questionForm.setRooftype(setRoofTypeFromInstalation(instalation));
        questionFormService.updateQuestionFormByInstalation(questionForm);
        technicalResultService.updateTR(instalation);
        priceService.updatePrice(instalation);
        productionService.updateProduction(instalation);
        instalationDBRepo.update(instalation);
    }

    private int setInstalationAngle(Instalation instalation) {
        log.info("Setting angle to questionform --service");
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        if (construction.getRooftype().toString().equals("DACH_SKOSNY")) {
            log.info("Setting instalation angle: " + instalation.getInstalationangle());
            return instalation.getInstalationangle();
        } else {
            log.info("Setting instalation angle: " + construction.getRoofslope());
            return construction.getRoofslope();
        }
    }

    private int setInstalationAngleQF(QuestionForm questionForm, Construction construction) {
        log.info("Setting angle to instalation --service");
        if (construction.getRooftype().toString().equals("DACH_SKOSNY")) {
            return questionForm.getRoofslope();
        } else {
            return construction.getRoofslope();
        }
    }

    private int setRoofPosition(QuestionForm questionForm) {
        return questionForm.getRoofposition();
    }

    private QuestionForm.RoofType setRoofTypeFromInstalation(Instalation instalation) {
        log.info("Setting roof type to questionform --service");
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        String rooftype = construction.getRooftype().toString();
        switch (rooftype) {
            case "DACH_PLASKI":
                return QuestionForm.RoofType.DACH_PLASKI;
            case "DACH_SKOSNY":
                return QuestionForm.RoofType.DACH_SKOSNY;
            case "GRUNT":
                return QuestionForm.RoofType.GRUNT;
            default: {
                return QuestionForm.RoofType.GRUNT;
            }
        }
    }

    private QuestionForm.RoofMaterial setRoofMaterialFromInstalation(Instalation instalation) {
        log.info("Setting roof material to questionform --service");
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        String material = construction.getRoofmaterial().toString();
        switch (material) {
            case "BLACHODACHOWKA":
                return QuestionForm.RoofMaterial.BLACHODACHOWKA;
            case "BLACHOTRAPEZ":
                return QuestionForm.RoofMaterial.BLACHOTRAPEZ;
            case "PLYTA_WARSTWOWA":
                return QuestionForm.RoofMaterial.PLYTA_WARSTWOWA;
            case "PAPA":
                return QuestionForm.RoofMaterial.PAPA;
            case "GONT":
                return QuestionForm.RoofMaterial.GONT;
            case "GRUNT":
                return QuestionForm.RoofMaterial.GRUNT;
            case "DACHÓWKA_CERAMICZNA":
                return QuestionForm.RoofMaterial.DACHÓWKA_CERAMICZNA;
            case "DACHÓWKA_KARPIÓWKA":
                return QuestionForm.RoofMaterial.DACHÓWKA_KARPIÓWKA;
            default: {
                return QuestionForm.RoofMaterial.SPECIFIED_BY_CONSTRUCTION;
            }
        }
    }


    private double roundResult(double number) {
        number = Math.round(number * 100);
        return number / 100;
    }

    private Construction getconstructionString(QuestionForm questionForm) {
        String rooftype = questionForm.getRooftype().toString();
        String roofmaterial = questionForm.getRoofmaterial().toString();
        log.info("Getting construcion by rooftype:" + rooftype + " and roofmaterial:" + roofmaterial);
        return constructionService.getConstructionByRoofTypeMaterial(rooftype, roofmaterial);
    }

    private int consumption(int bill) {
        log.info("Calculating consumption --service");
        double electricprice = 0.54;
        return (int) Math.round((bill / electricprice) * 12 * 1.2);
    }

    private Inverter getInverterByPower(double power) {
        log.info("Getting inverter --service");
        List<Inverter> inverterList = inverterService.getAllInverters();
        for (int i = 0; i < inverterList.size(); i++) {
            if (power > inverterList.get(i).getAcpower() && power <= inverterList.get(i).getDcpower()) {
                return inverterList.get(i);
            }
        }
        return inverterList.get(inverterList.size());
    }

    private int calcPower(int numberOfPVModules, PVModule pvModule) {
        log.info("Calculating power --service");
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
