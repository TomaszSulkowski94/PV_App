package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
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
public class InstalationDBRepoTest {

    @Autowired
    private InstalationDBRepo instalationDBRepo;

    @Autowired
    private PVModuleDBRepo pvModuleDBRepo;

    @Autowired
    private ConstructionDBRepo constructionDBRepo;

    @Autowired
    private InverterDBRepo inverterDBRepo;

    private PVModule pvModule;
    private Inverter inverter;
    private Construction construction;
    private Instalation instalation;
    private final List<Instalation> instalationList = new ArrayList<>();


    @Before
    public void setUp() {
        pvModule = pvModuleDBRepo.printbyid(1);
        inverter = inverterDBRepo.printbyid(1);
        construction = constructionDBRepo.printbyid(1);
        instalation = new Instalation(pvModule, 10, inverter, 1, construction, 20, 5);
        instalationDBRepo.create(instalation);
    }

    @After
    public void tearDown() {
        instalationList.clear();
        if (instalationDBRepo.printAll().contains(instalation)) {
            instalationDBRepo.delete(instalation.getId());
        }
    }

    @Test
    public void update() {
        //given
        instalation.setNumberofinverters(4);
        instalation.setNumberofpvmodule(30);
        int id = instalation.getId();
        //when
        instalationDBRepo.update(instalation);
        //then
        Assert.assertEquals(30, instalationDBRepo.printbyid(id).getNumberofpvmodule());
        Assert.assertEquals(4, instalationDBRepo.printbyid(id).getNumberofinverters());
    }

    @Test
    public void create() {
        //given
        Instalation testInstalation = new Instalation(pvModule, 20, inverter, 1, construction);
        //when
        instalationDBRepo.create(testInstalation);
        //then
        Assert.assertTrue(instalationDBRepo.printAll().contains(testInstalation));

    }

    @Test
    public void delete() {
        //given
        //when
        int id = instalation.getId();
        instalationDBRepo.delete(id);
        //then
        Assert.assertFalse(instalationDBRepo.printAll().contains(instalation));
    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(instalationDBRepo.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        int id = instalation.getId();
        //then
        Assert.assertNotNull(instalationDBRepo.printbyid(id));
    }

    @Test
    public void getByPVModule() {
        //given
        //when
        PVModule testPVModule = pvModuleDBRepo.printbyid(pvModule.getId());
        //then
        Assert.assertNotNull(instalationDBRepo.getByPVModule(testPVModule));
    }

    @Test
    public void getByConstruction() {
        //given
        //when
        Construction testConstruction = constructionDBRepo.printbyid(construction.getId());
        //then
        Assert.assertNotNull(instalationDBRepo.getByConstruction(testConstruction));
    }

    @Test
    public void getByInverter() {
        //given
        //when
        Inverter testInverter = inverterDBRepo.printbyid(inverter.getId());
        //then
        Assert.assertNotNull(instalationDBRepo.getByInverter(testInverter));
    }
}