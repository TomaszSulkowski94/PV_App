package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.PVModule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PVModuleServiceTest {

    @Autowired
    PVModuleService pvModuleService;

    private PVModule pvModule = new PVModule("Producent XYZ", "ZZZ315", PVModule.moduleType.MONOKRYSTALICZNY, 315, 9.87, 9.41, 40.94, 33.5, 0.25, 0.1903, 0.31);
    private List<PVModule> moduleList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        pvModuleService.saveModule(pvModule);
    }

    @After
    public void tearDown() throws Exception {
        moduleList.clear();
        if (pvModuleService.getAllModules().contains(pvModule)) {
            pvModuleService.deleteModule(pvModule.getId());
        }
    }

    @Test
    public void saveAll() {
        //given
        PVModule pvModule1 = new PVModule("Producent R", "RM310", PVModule.moduleType.MONOKRYSTALICZNY, 310, 10.01, 9.24, 40.1, 32.6, 0.21, 0.1860, 0.27);
        PVModule pvModule2 = new PVModule("Producent C", "CG320", PVModule.moduleType.POLIKRYSTALICZNY, 320, 10.01, 9.24, 40.2, 33.6, 0.22, 0.1860, 0.34);
        moduleList.add(pvModule1);
        moduleList.add(pvModule2);
        //when
        pvModuleService.saveAll(moduleList);
        //then
        Assert.assertTrue(pvModuleService.getAllModules().contains(pvModule1)
                && pvModuleService.getAllModules().contains(pvModule2));
    }

    @Test
    public void getAllModules() {
        //given
        //when
        //then
        Assert.assertNotNull(pvModuleService.getAllModules());
    }

    @Test
    public void saveModule() {
        //given
        PVModule testPvmodule = new PVModule("Producent R", "RM310", PVModule.moduleType.MONOKRYSTALICZNY, 310, 10.01, 9.24, 40.1, 32.6, 0.21, 0.1860, 0.27);
        //when
        pvModuleService.saveModule(testPvmodule);
        //then
        Assert.assertTrue(pvModuleService.getAllModules().contains(testPvmodule));

    }

    @Test
    public void getPVModule() {
        //given
        //when
        //then
        Assert.assertNotNull(pvModuleService.getPVModule(pvModule.getId()));
        Assert.assertEquals(pvModule,pvModuleService.getPVModule(pvModule.getId()));
    }

    @Test
    public void deleteModule() {
        //given
        int id = pvModule.getId();
        //when
        pvModuleService.deleteModule(id);
        //then
        Assert.assertFalse(pvModuleService.getAllModules().contains(pvModule));
    }

    @Test
    public void updatemodule() {
        //given
        pvModule.setPower(400);
        //when
        pvModuleService.updatemodule(pvModule);
        int updatedPower = pvModuleService.getPVModule(pvModule.getId()).getPower();
        //then
        Assert.assertEquals(400,updatedPower);
    }

    @Test
    public void saveFile() {
    }
}