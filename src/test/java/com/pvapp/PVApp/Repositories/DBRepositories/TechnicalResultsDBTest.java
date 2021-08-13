package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.TechnicalResults;
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
public class TechnicalResultsDBTest {

    @Autowired
    TechnicalResultsDB technicalResultsDB;

    private TechnicalResults tr = new TechnicalResults(51.0, 40.0, 30.0, 11.0, 9.0, 21, 5, 1);
    private List<TechnicalResults> trList = new ArrayList<>();


    @Before
    public void setUp() {
        technicalResultsDB.create(tr);
    }

    @After
    public void clean() {
        trList.clear();
        if (technicalResultsDB.printAll().contains(tr)) {
            technicalResultsDB.delete(tr.getId());
        }
    }

    @Test
    public void update() {
        //given
        tr.setIscmax(8);
        tr.setNmin(1);
        //when
        technicalResultsDB.update(tr);
        //then
        Assert.assertEquals(1, technicalResultsDB.printbyid(tr.getId()).getNmin(), 0);
        Assert.assertEquals(8, technicalResultsDB.printbyid(tr.getId()).getIscmax(), 0);
    }

    @Test
    public void create() {
        //given
        TechnicalResults tr1 = new TechnicalResults(51.0, 40.0, 30.0, 11.0, 9.0, 21, 5, 1);
        //when
        technicalResultsDB.create(tr1);
        //then
        Assert.assertTrue(technicalResultsDB.printAll().contains(tr1));
    }

    @Test
    public void delete() {
        //given
        //when
        technicalResultsDB.delete(tr.getId());
        //then
        Assert.assertNull(technicalResultsDB.printbyid(tr.getId()));

    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(technicalResultsDB.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        //then
        Assert.assertNotNull(technicalResultsDB.printbyid(tr.getId()));
    }
}