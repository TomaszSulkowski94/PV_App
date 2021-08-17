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

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductionServiceTest {

    @Autowired
    ProductionService productionService;

    @Autowired
    InstalationService instalationService;

    @Autowired
    PVModuleService pvModuleService;

    @Autowired
    InverterService inverterService;

    @Autowired
    ConstructionService constructionService;

    private Instalation instalation;


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
    public void createProduction() {
        //given
        //when
        //then
        instalation = instalationService.getById(instalation.getId());
        int idProduction = instalation.getProduction().getId();
        Assert.assertNotNull(productionService.getProduction(idProduction));
    }

    @Test
    public void updateProduction() {
        //given
        instalation.setNumberofpvmodule(5);
        int productionId = instalation.getProduction().getId();
        //when
        instalationService.update(instalation);
        double summary = productionService.getProduction(productionId).getSummary();
        //then
        Assert.assertEquals(1441.35, summary, 0);
    }

    @Test
    public void getProduction() {
        //given
        //when
        int id = instalation.getId();
        //then
        Assert.assertNotNull(productionService.getProduction(id));
    }

    @Test
    public void electricFactorTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = ProductionService.class.getDeclaredMethod("electricFactor", Instalation.class);
        method.setAccessible(true);
        //when
        double electricFactor = (double) method.invoke(productionService,instalation);
        //then
        Assert.assertEquals(1.09,electricFactor,0.01);
    }

    @Test
    public void calcProductionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ProductionService.class.getDeclaredMethod("calcProduction", Instalation.class);
        method.setAccessible(true);
        //given
        //when
        double [] productionArray= (double[]) method.invoke(productionService,instalation);
        double summaryProd = productionArray[12];
        //then
        Assert.assertEquals(2883,summaryProd,5);
    }
}