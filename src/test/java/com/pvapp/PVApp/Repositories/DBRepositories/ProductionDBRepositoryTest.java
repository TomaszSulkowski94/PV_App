package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Production;
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
public class ProductionDBRepositoryTest {

    @Autowired
    ProductionDBRepository productionDBRepository;

    private Production production = new Production(100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 1200);
    private List<Production> productionList = new ArrayList<>();

    @Before
    public void setUp() {
        productionDBRepository.create(production);
    }

    @After
    public void clear() {
        productionList.clear();
        if (productionDBRepository.printAll().contains(production)) {
            productionDBRepository.delete(production.getId());
        }
    }

    @Test
    public void update() {
        //given
        //when
        production.setSummary(1400);
        productionDBRepository.update(production);
        //then
        Assert.assertEquals(1400,productionDBRepository.printbyid(production.getId()).getSummary(),0);
    }

    @Test
    public void create() {
        //given
        Production production1 = new Production(100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 1200);
        //when
        productionDBRepository.create(production1);
        //then
        Assert.assertTrue(productionDBRepository.printAll().contains(production1));
    }

    @Test
    public void delete() {
        //given
        //when
        productionDBRepository.delete(production.getId());
        //then
        Assert.assertFalse(productionDBRepository.printAll().contains(production.getId()));
    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(productionDBRepository.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        //then
        Assert.assertNotNull(productionDBRepository.printbyid(production.getId()));
    }
}