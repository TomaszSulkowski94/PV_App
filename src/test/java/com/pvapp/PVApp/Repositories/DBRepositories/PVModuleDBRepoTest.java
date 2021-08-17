package com.pvapp.PVApp.Repositories.DBRepositories;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PVModuleDBRepoTest {

    @Autowired
    PVModuleDBRepo pvModuleDBRepo;

    private PVModule pvModule = new PVModule("Producent XYZ", "ZZZ315", PVModule.moduleType.MONOKRYSTALICZNY, 315, 9.87, 9.41, 40.94, 33.5, 0.25, 0.1903, 0.31);
    private List<PVModule> pvModuleList = new ArrayList<>();

    @Before
    public void setUp() {
        pvModuleDBRepo.create(pvModule);
    }

    @After
    public void clear() {
        pvModuleList.clear();
        if (pvModuleDBRepo.printAll().contains(pvModule)) {
            pvModuleDBRepo.delete(pvModule.getId());
        }
    }

    @Test
    public void saveAll() {
        //given
        List<PVModule> modulesToADD = new ArrayList<>();
        PVModule pvModule1 = new PVModule("ABC", "1234", PVModule.moduleType.MONOKRYSTALICZNY, 315, 9.87, 9.41, 40.94, 33.5, 0.25, 0.1903, 0.31);
        PVModule pvModule2 = new PVModule("ABC1", "12345", PVModule.moduleType.MONOKRYSTALICZNY, 315, 9.87, 9.41, 40.94, 33.5, 0.25, 0.1903, 0.31);
        modulesToADD.add(pvModule1);
        modulesToADD.add(pvModule2);
        //when
        pvModuleDBRepo.saveAll(modulesToADD);
        pvModuleList = new ArrayList<>(pvModuleDBRepo.printAll());
        //then
        Assert.assertTrue(pvModuleList.contains(pvModule1));
        Assert.assertTrue(pvModuleList.contains(pvModule2));
    }

    @Test
    public void update() {
        //given
        pvModule.setPower(400);
        pvModule.setPrice(1);
        //when
        pvModuleDBRepo.update(pvModule);
        //then
        Assert.assertTrue((400 == pvModuleDBRepo.printbyid(pvModule.getId()).getPower()
                && 1 == pvModuleDBRepo.printbyid(pvModule.getId()).getPrice()));
    }

    @Test
    public void create() {
        //given
        PVModule pvModule1 = new PVModule("ABC", "1234", PVModule.moduleType.MONOKRYSTALICZNY, 315, 9.87, 9.41, 40.94, 33.5, 0.25, 0.1903, 0.31);
        //when
        pvModuleDBRepo.create(pvModule1);
        //then
        Assert.assertTrue(pvModuleDBRepo.printAll().contains(pvModule1));
    }

    @Test
    public void delete() {
        //given
        //when
        pvModuleDBRepo.delete(pvModule.getId());
        //then
        Assert.assertFalse(pvModuleDBRepo.printAll().contains(pvModule));
    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(pvModuleDBRepo.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        //then
        Assert.assertNotNull(pvModuleDBRepo.printbyid(1));
    }
}