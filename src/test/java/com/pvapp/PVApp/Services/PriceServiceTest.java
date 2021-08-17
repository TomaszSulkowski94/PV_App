package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PriceServiceTest {

    @Autowired
    PriceService priceService;

    @Autowired
    InstalationService instalationService;

    @Autowired
    PVModuleService pvModuleService;

    @Autowired
    InverterService inverterService;

    @Autowired
    ConstructionService constructionService;

    private Instalation instalation;
    private final List<Price> priceList = new ArrayList<>();
    private Price price;


    @Before
    public void setUp() {
        PVModule pvModule = pvModuleService.getPVModule(1);
        Inverter inverter = inverterService.getInverter(1);
        Construction construction = constructionService.getConstruction(1);
        instalation = new Instalation(pvModule, 10, inverter, 1, construction, 20, 5);
        instalationService.save(instalation);
    }

    @After
    public void tearDown() {
        if (instalationService.getAll().contains(instalation)) {
            instalationService.delete(instalation.getId());
        }
    }


    @Test
    public void getAllPrices() {
        //given
        //when
        //then
        Assert.assertNotNull(priceService.getAllPrices());
    }

    @Test
    public void setDiscount() {
        //given
        price = instalation.getPrice();
        //when
        price.setDiscount(10);
        priceService.setDiscount(price);
        //then
        Assert.assertEquals(9490, priceService.getPrice(price.getId()).getDiscountedinstalationpricegross(), 5);
    }

    @Test
    public void createPrice() {
        //given
        //when
        //then
        Assert.assertNotNull(instalation.getPrice().getId());
    }

    @Test
    public void deletePrice() {
        //given
        price = priceService.getPrice(instalation.getPrice().getId());
        //when
        instalationService.delete(instalation.getId());
        //then
        Assert.assertFalse(priceService.getAllPrices().contains(price));
    }

    @Test
    public void getPrice() {
        //given
        //when
        price = priceService.getPrice(instalation.getProduction().getId());
        //then
        Assert.assertNotNull(price);
    }

    @Test
    public void calcPriceTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        price = instalation.getPrice();
        Method method = PriceService.class.getDeclaredMethod("calcPrice", Price.class, Instalation.class);
        method.setAccessible(true);
        //when
        method.invoke(priceService, price, instalation);
        double sumPrice = priceService.getPrice(instalation.getPrice().getId()).getInstalationpricegross();
        //then
        Assert.assertEquals(10546,sumPrice,1);
    }

    @Test
    public void calcGrossPrice() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        price = priceService.getPrice(instalation.getPrice().getId());
        Method method = PriceService.class.getDeclaredMethod("calcModulePrice", PVModule.class, Instalation.class);
        method.setAccessible(true);
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        double modulePrice = (double) method.invoke(priceService, pvModule, instalation);
        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());
        method = PriceService.class.getDeclaredMethod("calcConstructionPrice", Instalation.class);
        method.setAccessible(true);
        double constructionPrice = (double) method.invoke(priceService, instalation);
        method = PriceService.class.getDeclaredMethod("calcInstalationPriceNet", double.class, double.class, Inverter.class);
        method.setAccessible(true);
        double netPrice = (double) method.invoke(priceService, modulePrice, constructionPrice, inverter);

        //when
        method = PriceService.class.getDeclaredMethod("calcGrossPrice", double.class, double.class);
        method.setAccessible(true);
        double grossPrice = (double) method.invoke(priceService, netPrice, 8);

        //then
        Assert.assertEquals(10546, grossPrice, 1);
    }

    @Test
    public void calcInstalationPriceNet() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = PriceService.class.getDeclaredMethod("calcModulePrice", PVModule.class, Instalation.class);
        method.setAccessible(true);
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        double modulePrice = (double) method.invoke(priceService, pvModule, instalation);

        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());

        method = PriceService.class.getDeclaredMethod("calcConstructionPrice", Instalation.class);
        method.setAccessible(true);
        double constructionPrice = (double) method.invoke(priceService, instalation);

        method = PriceService.class.getDeclaredMethod("calcInstalationPriceNet", double.class, double.class, Inverter.class);
        method.setAccessible(true);
        //when
        double netPrice = (double) method.invoke(priceService, modulePrice, constructionPrice, inverter);
        //then
        Assert.assertEquals(9766, netPrice, 1);
    }


    //    private double calcInstalationPriceNet(double modulePrice, double costructionPrice, Inverter inverter) {
//        return round(modulePrice + costructionPrice + inverter.getPrice());
//    }
//
    @Test
    public void calcModulePrice() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = PriceService.class.getDeclaredMethod("calcModulePrice", PVModule.class, Instalation.class);
        method.setAccessible(true);
        //given
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        //when
        double price = (double) method.invoke(priceService, pvModule, instalation);
        //then
        Assert.assertEquals(4065, price, 1);
    }

    @Test
    public void calcInverterPrice() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = PriceService.class.getDeclaredMethod("calcInverterPrice", Inverter.class, Instalation.class);
        method.setAccessible(true);
        //given
        Inverter inverter = inverterService.getInverter(instalation.getInverter().getId());
        //when
        double price = (double) method.invoke(priceService, inverter, instalation);
        //then
        Assert.assertEquals(4200, price, 1);
    }

    @Test
    public void calcConstructionPrice() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = PriceService.class.getDeclaredMethod("calcConstructionPrice", Instalation.class);
        method.setAccessible(true);
        //given
        //when
        double price = (double) method.invoke(priceService, instalation);
        //then
        Assert.assertEquals(1500, price, 1);
    }

}