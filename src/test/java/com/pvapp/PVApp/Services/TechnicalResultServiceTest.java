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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TechnicalResultServiceTest {

    @Autowired
    TechnicalResultService technicalResultService;

    @Autowired
    InstalationService instalationService;

    @Autowired
    PVModuleService pvModuleService;

    @Autowired
    InverterService inverterService;

    @Autowired
    ConstructionService constructionService;

    private Instalation instalation;
    private TechnicalResults tr;
    private PVModule pvModule;
    private Inverter inverter;

    @Before
    public void setUp() {
        pvModule = pvModuleService.getPVModule(1);
        inverter = inverterService.getInverter(1);
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
    public void updateTR() {
        //given
        tr = instalation.getTechnicalResults();
        //when
        instalation.setPvModule(pvModuleService.getPVModule(2));
        technicalResultService.updateTR(instalation);
        //then
        tr = technicalResultService.getTR(instalation.getTechnicalResults().getId());
        Assert.assertEquals(30, tr.getVmppmin(), 1);
    }

    @Test
    public void getTR() {
        //given
        //when
        //then
        Assert.assertNotNull(technicalResultService.getTR(instalation.getTechnicalResults().getId()));
    }

    @Test
    public void calcVocMax() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = TechnicalResultService.class.getDeclaredMethod("calcvVocmax", PVModule.class);
        method.setAccessible(true);
        //when
        double vocMax = (double) method.invoke(technicalResultService, pvModule);
        //then
        Assert.assertEquals(49, vocMax, 1);
    }

    @Test
    public void calcVmppMax() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = TechnicalResultService.class.getDeclaredMethod("calcVmppMax", PVModule.class);
        method.setAccessible(true);
        //when
        double vmppMax = (double) method.invoke(technicalResultService, pvModule);
        //then
        Assert.assertEquals(39, vmppMax, 1);
    }

    @Test
    public void calcVmppMin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = TechnicalResultService.class.getDeclaredMethod("calcVmppmin", PVModule.class);
        method.setAccessible(true);
        //when
        double vmppMin = (double) method.invoke(technicalResultService, pvModule);
        //then
        Assert.assertEquals(33, vmppMin, 1);
    }

    @Test
    public void calcIscMax() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = TechnicalResultService.class.getDeclaredMethod("calcIscMax", PVModule.class);
        method.setAccessible(true);
        //when
        double iscMax = (double) method.invoke(technicalResultService, pvModule);
        //then
        Assert.assertEquals(11, iscMax, 1);
    }

    @Test
    public void calcImppMax() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Method method = TechnicalResultService.class.getDeclaredMethod("calcImppMax", PVModule.class);
        method.setAccessible(true);
        //when
        double imppMax = (double) method.invoke(technicalResultService, pvModule);
        //then
        Assert.assertEquals(9, imppMax, 1);
    }

    @Test
    public void calcMaxNumberOfModulesAtString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TechnicalResultService.class.getDeclaredMethod("calcvVocmax", PVModule.class);
        method.setAccessible(true);
        double vocMax = (double) method.invoke(technicalResultService, pvModule);

        method = TechnicalResultService.class.getDeclaredMethod("calcVmppMax", PVModule.class);
        method.setAccessible(true);
        double vmppMax = (double) method.invoke(technicalResultService, pvModule);
        //when
        method = TechnicalResultService.class.getDeclaredMethod("calcMaxNumberOfModulesAtString", Inverter.class, double.class, double.class);
        method.setAccessible(true);
        int numberOfPVModules = (int) method.invoke(technicalResultService, inverter, vocMax, vmppMax);
        //then
        Assert.assertEquals(21, numberOfPVModules, 0);
    }

    @Test
    public void calccalcMinNumberOfModulesAtString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TechnicalResultService.class.getDeclaredMethod("calcVmppmin", PVModule.class);
        method.setAccessible(true);
        double vmppMin = (double) method.invoke(technicalResultService, pvModule);

        method = TechnicalResultService.class.getDeclaredMethod("calcMinNumberOfModulesAtString", Inverter.class, double.class);
        method.setAccessible(true);
        //given
        //when
        int minNumberOfModulesAtString = (int) method.invoke(technicalResultService,inverter,vmppMin);
        //then
        Assert.assertEquals(4,minNumberOfModulesAtString,0);
    }

    @Test
    public void calcMaxParallelString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TechnicalResultService.class.getDeclaredMethod("calcIscMax", PVModule.class);
        method.setAccessible(true);
        double iscMax = (double) method.invoke(technicalResultService, pvModule);

        method = TechnicalResultService.class.getDeclaredMethod("calcImppMax", PVModule.class);
        method.setAccessible(true);
        double imppMax = (double) method.invoke(technicalResultService, pvModule);

        method = TechnicalResultService.class.getDeclaredMethod("calcMaxNumberOfModulesAtString", Inverter.class, double.class, double.class);
        method.setAccessible(true);
        //given
        //when
        int minNumberOfModulesAtString = (int) method.invoke(technicalResultService,inverter,iscMax,imppMax);
        //then
        Assert.assertEquals(88,minNumberOfModulesAtString,0);
    }
}