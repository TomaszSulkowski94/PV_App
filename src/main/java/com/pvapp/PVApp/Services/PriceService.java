package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.*;
import com.pvapp.PVApp.Repositories.DBRepositories.PriceDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PriceService {

    @Autowired
    PriceDBRepo priceDBRepo;


    @Autowired
    PVModuleService pvModuleService;

    @Autowired
    ConstructionService constructionService;

    @Autowired
    InverterService inverterService;

    public List<Price> getAllPrices() {
        log.info("Getting all prices --service");
        return new ArrayList<Price>(priceDBRepo.printAll());
    }

    private void calcPrice(Price price, Instalation instalation) {
        log.info("Calculating price --service");
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());
        double modulePrice = calcModulePrice(pvModule, instalation);
        price.setModuleprice(modulePrice);
        log.info("Module price: " + modulePrice);
        double constructionPrice = calcConstructionPrice(instalation);
        log.info("Construction price: " + constructionPrice);
        price.setConstructionprice(constructionPrice);
        price.setInverterprice(inverter.getPrice());
        double netPrice = calcInstalationPriceNet(modulePrice, constructionPrice, inverter);
        price.setInstalationpricenet(netPrice);
        log.info("Net price: " + netPrice);
        price.setTaxvalue(round(netPrice * 0.08));
        double grossPrice = calcGrossPrice(netPrice, 8);
        log.info("Gross price: " + grossPrice);
        price.setInstalationpricegross(grossPrice);
        price.setDiscountedinstalationpricegross(grossPrice);
    }


    private double calcGrossPrice(double netPrice, double tax) {
        return round(netPrice * (tax / 100) + netPrice);
    }

    private double calcInstalationPriceNet(double modulePrice, double costructionPrice, Inverter inverter) {
        return round(modulePrice + costructionPrice + inverter.getPrice());
    }

    private double calcModulePrice(PVModule pvModule, Instalation instalation) {
        return round(instalation.getPower() * pvModule.getPrice() * 4.45);
    }

    private double round(double number) {
        number = Math.round(number * 100);
        return number / 100;
    }

    private double calcConstructionPrice(Instalation instalation) {
        return round(instalation.getNumberofpvmodule() * constructionService.getConstruction(instalation.getConstruction().getId()).getPrice());
    }

    public void updatePrice(Instalation instalation) {
        log.info("Recalculating price --service");
        Price price = getPrice(instalation.getPrice().getId());
        calcPrice(price, instalation);
        update(price);
    }

    public void update(Price price) {
        log.info("Updating price --service");
        priceDBRepo.update(price);
    }

    public void setDiscount(Price price) {
        int discount = price.getDiscount();
        double doublDiscount = (double) discount / 100;
        double newGrossPrice = round(price.getInstalationpricegross() - price.getInstalationpricegross() * doublDiscount);
        price.setDiscountedinstalationpricegross(newGrossPrice);
        update(price);
    }

    public void createPrice(Instalation instalation) {
        log.info("Saving price --service");
        Price price = new Price();
        calcPrice(price, instalation);
        priceDBRepo.create(price);
        instalation.setPrice(price);
    }

    public void deletePrice(int id) {
        log.info("Deleting price --service");
        priceDBRepo.delete(id);
    }

    public Price getPrice(int id) {
        log.info("Getting price by id --service");
        return priceDBRepo.printbyid(id);
    }


}
