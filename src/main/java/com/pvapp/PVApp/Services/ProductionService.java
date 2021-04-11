package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Production;

import com.pvapp.PVApp.Repositories.DBRepositories.ProductionDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductionService {

    @Autowired
    ProductionDBRepository productionDBRepository;

    public void calcProduction(Instalation instalation, Production production) {
        double[] productionfactors = {0.03, 0.04, 0.07, 0.09, 0.12, 0.13, 0.12, 0.13, 0.11, 0.09, 0.05, 0.03};
        double[] productionArray = new double[12];
        double calcProduction;
        double summary = 0;
        for (int i = 0; i < productionfactors.length; i++) {
            log.info("Calculating production for " + (i + 1) + " month");
            calcProduction = instalation.getPower() * productionfactors[i];
            calcProduction = Math.round(calcProduction * 100) / 100;
            productionArray[i] = calcProduction;
            summary += calcProduction;
        }
        production.setJanuary(productionArray[0]);
        production.setFebruary(productionArray[1]);
        production.setMarch(productionArray[2]);
        production.setApril(productionArray[3]);
        production.setMay(productionArray[4]);
        production.setJune(productionArray[5]);
        production.setJuly(productionArray[6]);
        production.setAugust(productionArray[7]);
        production.setSeptember(productionArray[8]);
        production.setOctober(productionArray[9]);
        production.setNovember(productionArray[10]);
        production.setDecember(productionArray[11]);
        production.setSummary(summary);
    }

    public Production createProduction(Instalation instalation) {
        Production production = new Production();
        calcProduction(instalation, production);
        productionDBRepository.create(production);
        return production;
    }


    public void updateProduction(Instalation instalation) {
        Production production = productionDBRepository.printbyid(instalation.getProduction().getId());
        calcProduction(instalation, production);
        productionDBRepository.update(production);
    }

    public void deleteProduction(int id) {
        productionDBRepository.delete(id);
    }

    public Production getProduction(int id) {
        return productionDBRepository.printbyid(id);
    }

    public List<Production> getProduction() {
        return new ArrayList<Production>(productionDBRepository.printAll());
    }
}
