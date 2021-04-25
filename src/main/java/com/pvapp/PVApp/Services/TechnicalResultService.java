package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Entities.TechnicalResults;
import com.pvapp.PVApp.Repositories.DBRepositories.TechnicalResultsDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TechnicalResultService {

    @Autowired
    TechnicalResultsDB technicalResultsDB;

    @Autowired
    InstalationService instalationService;

    @Autowired
    PVModuleService pvModuleService;

    @Autowired
    InverterService inverterService;

    public TechnicalResults createTR(Instalation instalation) {
        log.info("Saving technical results --service");
        TechnicalResults technicalResults = new TechnicalResults();
        calcTR(instalation, technicalResults);
        technicalResultsDB.create(technicalResults);
        instalation.setTechnicalResults(technicalResults);
        return technicalResults;
    }

    private void calcTR(Instalation instalation, TechnicalResults technicalResults) {
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        double vocMax = calcvVocmax(pvModule);
        double vmppMax = calcVmppMax(pvModule);
        double vmppMin = calcVmppmin(pvModule);
        double iscMax = calcIscMax(pvModule);
        double imppMax = calcImppMax(pvModule);
        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());
        technicalResults.setVocmax(vocMax);
        technicalResults.setVmppmin(vmppMin);
        technicalResults.setVmppmax(vmppMax);
        technicalResults.setIscmax(iscMax);
        technicalResults.setImppmax(imppMax);
        technicalResults.setNmax(calcMaxNumberOfModulesAtString(inverter, vocMax, vmppMax));
        technicalResults.setNmaxparallel(calcMaxParallelString(inverter, iscMax, imppMax));
        technicalResults.setNmin(calcMinNumberOfModulesAtString(inverter, vmppMin));
        technicalResults.setInstalation(instalation);
    }

    public void deleteTR(int id) {
        log.info("Deleting technical results --service");
        technicalResultsDB.delete(id);
    }

    public void updateTR(Instalation instalation) {
        log.info("Deleting technical results --service");
        TechnicalResults technicalResults = getTR(instalation.getTechnicalResults().getId());
        calcTR(instalation, technicalResults);
        technicalResultsDB.update(technicalResults);
    }

    public TechnicalResults getTR(int id) {
        log.info("Getting technical results --service");
        return technicalResultsDB.printbyid(id);
    }

    public List<TechnicalResults> getAllTR() {
        log.info("Getting all technical results --service");
        return new ArrayList<TechnicalResults>(technicalResultsDB.printAll());
    }

    private double roundTwoPlaces(double number) {
        return Math.round(number * 100) / 100;
    }

    private double calcvVocmax(PVModule pvModule) {
        double vocSTC = pvModule.getVoltageSTC();
        return roundTwoPlaces(vocSTC + ((pvModule.getTemperatureLost() / 100) * vocSTC * 45));
    }

    private double calcVmppMax(PVModule pvModule) {
        return roundTwoPlaces(pvModule.getVoltageMPP() + ((pvModule.getTemperatureLost() / 100) * pvModule.getVoltageSTC() * 25));
    }

    private double calcVmppmin(PVModule pvModule) {
        return roundTwoPlaces(pvModule.getVoltageMPP() - ((pvModule.getTemperatureLost() / 100) * pvModule.getVoltageSTC() * 45));
    }

    private double calcIscMax(PVModule pvModule) {
        return roundTwoPlaces(pvModule.getCurrentSTC() * 1.25);
    }

    private double calcImppMax(PVModule pvModule) {
        return roundTwoPlaces(pvModule.getMaxCurrentSTC() * 1.15);
    }

    private int calcMaxNumberOfModulesAtString(Inverter inverter, double vocmax, double vmppmax) {
        double umax = inverter.getMaksymalnenapiecie();
        double umpptmax = inverter.getGornyzakresnapiecia();
        if ((umax / vocmax) > (umpptmax / vmppmax)) {
            return (int) Math.floor(umax / vocmax);
        } else {
            return (int) Math.floor(umpptmax / vmppmax);
        }
    }

    private int calcMinNumberOfModulesAtString(Inverter inverter, double vmppmin) {
        return (int) Math.floor(inverter.getDolnyzakresnapiecia() / vmppmin);
    }

    private int calcMaxParallelString(Inverter inverter, double iscmax, double imppmax) {
        double ifmax = inverter.getMaxcurrentzwarcia();
        double ifrob = inverter.getMaxcurrentrob();
        if ((ifmax / iscmax) > (ifrob / imppmax)) {
            return (int) Math.floor(ifmax / iscmax);
        } else {
            return (int) Math.floor(ifrob / imppmax);
        }
    }

}
