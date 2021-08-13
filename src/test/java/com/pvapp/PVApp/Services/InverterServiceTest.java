package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Inverter;
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
public class InverterServiceTest {

    @Autowired
    private InverterService inverterService;
    private List<Inverter> invList = new ArrayList<>();
    private Inverter inverter = new Inverter("Producent Z", "XYZ", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);

    @Before
    public void setUp() {
        inverterService.saveInverter(inverter);
    }

    @After
    public void tearDown() {
        invList.clear();
        if (inverterService.getAllInverters().contains(inverter)) {
            inverterService.deleteInverter(inverter.getId());
        }
    }

    @Test
    public void getAllInverters() {
        //given
        //when
        //then
        Assert.assertNotNull(inverterService.getAllInverters());
    }

    @Test
    public void saveInverter() {
        //given
        Inverter testInv = new Inverter("Producent XZZ", "1234", Inverter.InverterType.TROJFAZOWY, 3000, 2000, 1, 11.00, 14.00, 160, 840, 1000, 4500);
        //when
        inverterService.saveInverter(testInv);
        //then
        Assert.assertTrue(inverterService.getAllInverters().contains(testInv));
    }

    @Test
    public void deleteInverter() {
        //given
        int id = inverter.getId();
        //when
        inverterService.deleteInverter(id);
        //then
        Assert.assertFalse(inverterService.getAllInverters().contains(inverter));
    }

    @Test
    public void getInverter() {
        //given
        //when
        //then
        Assert.assertNotNull(inverterService.getInverter(inverter.getId()));
    }

    @Test
    public void update() {
        //given
        inverter.setPrice(10000);
        //when
        inverterService.update(inverter);
        double price = inverterService.getInverter(inverter.getId()).getPrice();
        //then
        Assert.assertEquals(10000, price, 0);
    }

    @Test
    public void saveFile() {
    }
}