package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Inverter;
import lombok.extern.slf4j.Slf4j;
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
public class InverterDBRepoTest {

    @Autowired
    private InverterDBRepo inverterDBRepo;

    private Inverter inverter = new Inverter("Producent Z", "XYZ", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
    private List<Inverter> invList = new ArrayList<>();

    @Before
    public void setUp() {
        inverterDBRepo.create(inverter);
    }

    @After
    public void clear() {
        if (inverterDBRepo.printAll().contains(inverter)) {
            inverterDBRepo.delete(inverter.getId());
        }
        invList.clear();
    }


    @Test
    public void testCreateInverter() {
        //given
        Inverter inverter1 = new Inverter("Producent ZZZ", "XYZXYZ", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
        //when
        inverterDBRepo.create(inverter1);
        invList = new ArrayList<>(inverterDBRepo.printAll());
        //then
        Assert.assertTrue(invList.contains(inverter1));
    }

    @Test
    public void testSaveInverterList() {
        //given
        Inverter inverter1 = new Inverter("Producent XXXXX", "XYZXYZ", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
        Inverter inverter2 = new Inverter("Producent YYYYY", "XYZXYZ", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
        invList.add(inverter1);
        invList.add(inverter2);
        //when
        inverterDBRepo.saveAll(invList);
        //then
        List<Inverter> newInvList = new ArrayList<>(inverterDBRepo.printAll());
        Assert.assertTrue(newInvList.contains(inverter1));
        Assert.assertTrue(newInvList.contains(inverter2));
    }

    @Test
    public void testGetInverterById() {
        //given
        //when
        //then
        Assert.assertNotNull(inverterDBRepo.printbyid(1));
    }

    @Test
    public void testPrintAllInverters() {
        //given
        //when
        //then
        Assert.assertNotNull(inverterDBRepo.printAll());
    }

    @Test
    public void testDeleteInverter() {
        //given
        //when
        inverterDBRepo.delete(inverter.getId());
        invList = new ArrayList<>(inverterDBRepo.printAll());
        //then
        Assert.assertFalse(invList.contains(inverter));
    }

    @Test
    public void testUpdateInverter() {
        //given
        inverter = inverterDBRepo.printbyid(inverter.getId());
        //when
        inverter.setPrice(1500);
        inverterDBRepo.update(inverter);
        //then
        Assert.assertEquals(1500, inverterDBRepo.printbyid(inverter.getId()).getPrice(), 0.0);
    }
}