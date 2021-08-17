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

@SpringBootTest
@RunWith(SpringRunner.class)
public class InstalationServiceTest {

    @Autowired
    private InstalationService instalationService;

    @Autowired
    private PVModuleService pvModuleService;

    @Autowired
    private ConstructionService constructionService;

    @Autowired
    private QuestionFormService questionFormService;

    @Autowired
    private InverterService inverterService;

    private PVModule pvModule;
    private Inverter inverter;
    private Construction construction;
    private Instalation instalation;
    private final List<Instalation> instalationList = new ArrayList<>();
    private final QuestionForm questionForm = new QuestionForm(50, QuestionForm.RoofType.DACH_PLASKI, 0, 20, QuestionForm.RoofMaterial.PAPA);

    @Before
    public void setUp() throws Exception {
        pvModule = pvModuleService.getPVModule(1);
        inverter = inverterService.getInverter(1);
        construction = constructionService.getConstruction(1);
        instalation = new Instalation(pvModule, 10, inverter, 1, construction, 20, 5);
        instalationService.save(instalation);
    }

    @After
    public void tearDown() throws Exception {
        instalationList.clear();
        if (instalationService.getAll().contains(instalation)) {
            instalationService.delete(instalation.getId());
        }
    }

    @Test
    public void getAll() {
        //given
        //when
        //then
        Assert.assertNotNull(instalationService.getAll());
    }

    @Test
    public void getAllByPVModule() {
        //given
        //when
        //then
        Assert.assertNotNull(instalationService.getAllByPVModule(pvModule));
    }

    @Test
    public void getAllByConstruction() {
        //given
        //when
        //then
        Assert.assertNotNull(instalationService.getAllByConstruction(construction));
    }

    @Test
    public void getAllByInverter() {
        //given
        //when
        //then
        Assert.assertNotNull(instalationService.getAllByInverter(inverter));
    }

    @Test
    public void save() {
        //given
        Instalation testInstalation = new Instalation(pvModule, 22, inverter, 1, construction, 20, 5);
        //when
        instalationService.save(testInstalation);
        //then
        Assert.assertTrue(instalationService.getAll().contains(testInstalation));
    }

    @Test
    public void createInstalation() {
        //given
        QuestionForm qf = new QuestionForm(200, QuestionForm.RoofType.DACH_SKOSNY, 20, 0, QuestionForm.RoofMaterial.GONT);
        questionFormService.create(qf);
        //when
        instalationService.createInstalation(qf);
        //then
        Assert.assertNotNull(instalationService.getByQF(qf));
    }

    @Test
    public void updateByQuestionForm() {
        //given
        QuestionForm qf = new QuestionForm(200, QuestionForm.RoofType.DACH_SKOSNY, 20, 0, QuestionForm.RoofMaterial.GONT);
        questionFormService.createQuestionForm(qf);
        instalation = instalationService.getByQF(qf);
        qf.setBill(100);
        questionFormService.updateQuestionFormByInstalation(qf);
        //when
        instalationService.updateByQuestionForm(qf);
        //then
        Assert.assertEquals(100, qf.getBill(), 0);
        Assert.assertEquals(2520, instalationService.getById(instalation.getId()).getPower(), 0);
    }

    @Test
    public void update() {
        //given
        instalation.setNumberofpvmodule(50);
        //when
        instalationService.update(instalation);
        //then
        Assert.assertEquals(50, instalationService.getById(instalation.getId()).getNumberofpvmodule());
    }

    @Test
    public void delete() {
        //given
        //when
        int id = instalation.getId();
        instalationService.delete(id);
        //then
        Assert.assertFalse(instalationService.getAll().contains(instalationService.getById(id)));
    }

    @Test
    public void getById() {
        //given
        //when
        //then
        Assert.assertEquals(instalation, instalationService.getById(instalation.getId()));
    }

    @Test
    public void calcPower() {
        //given
        PVModule pvModule = instalation.getPvModule();
        int numberOfPVModules = instalation.getNumberofpvmodule();
        //when
        int power = instalationService.calcPower(numberOfPVModules, pvModule);
        //then
        Assert.assertEquals(3150, power);
    }

    @Test
    public void getconstructionString() {
        //given
        questionFormService.createQuestionForm(questionForm);
        //when
        Construction resultConstruction = instalationService.getconstructionString(questionForm);
        //then
        Assert.assertEquals(construction.getRooftype().toString(), resultConstruction.getRooftype().toString());
        Assert.assertEquals(construction.getRoofmaterial().toString(), resultConstruction.getRoofmaterial().toString());
        Assert.assertEquals(construction, resultConstruction);
    }

    @Test
    public void consumption() {
        //given
        questionFormService.createQuestionForm(questionForm);
        int bill = questionForm.getBill();
        //when
        double yearConsumption = instalationService.consumption(bill);
        //then
        Assert.assertEquals(1333, yearConsumption, 0);
    }

    @Test
    public void getInverterByPower() {
        //given
        Inverter excpectedInverter = inverterService.getInverter(4);
        //when
        Inverter resultInverter = instalationService.getInverterByPower(instalation.getPower());
        //then
        Assert.assertEquals(excpectedInverter, resultInverter);
    }

    // PRIVATE METHOD TEST
//    @Test
//    public void calcPowerTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        //given
//        Method method = InstalationService.class.getDeclaredMethod("calcPower", Integer.class, PVModule.class);
//        method.setAccessible(true);
//        //when
//        int numberOfModules = instalation.getNumberofpvmodule();
//        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
//        int power = (int) method.invoke(instalationService, numberOfModules,pvModule);
//        //then
//        Assert.assertEquals(3150, power);
//    }

}