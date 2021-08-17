package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Price;
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
public class PriceDBRepoTest {

    @Autowired
    PriceDBRepo priceDBRepo;

    private Price price = new Price(7569.45, 4200.0, 3000.0, 14769.45, 1181.56, 15951.01, 15951.01, 0);
    private List<Price> priceList = new ArrayList<>();

    @Before
    public void setUp() {
        priceDBRepo.create(price);
    }

    @After
    public void clear() {
        priceList.clear();
        if (priceDBRepo.printAll().contains(price)) {
            priceDBRepo.delete(price.getId());
        }
    }

    @Test
    public void update() {
        //given
        price.setDiscount(20);
        price.setDiscountedinstalationpricegross(16000);
        //when
        priceDBRepo.update(price);
        //then
        Assert.assertEquals(16000,priceDBRepo.printbyid(price.getId()).getDiscountedinstalationpricegross(),0);
        Assert.assertEquals(20,priceDBRepo.printbyid(price.getId()).getDiscount(),0);
    }

    @Test
    public void create() {
        //given
        Price price1 = new Price(8000, 6000, 4000, 18000, 2000, 20000, 18000, 10);
        //when
        priceDBRepo.create(price1);
        //then
        Assert.assertTrue(priceDBRepo.printAll().contains(price1));
    }

    @Test
    public void delete() {
        //given
        //when
        priceDBRepo.delete(price.getId());
        //then
        Assert.assertFalse(priceDBRepo.printAll().contains(price));
        Assert.assertFalse(priceDBRepo.printAll().contains(priceDBRepo.printbyid(price.getId())));
    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(priceDBRepo.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        //then
        Assert.assertNotNull(priceDBRepo.printbyid(price.getId()));
    }
}