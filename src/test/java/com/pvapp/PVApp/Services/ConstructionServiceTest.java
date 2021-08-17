package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
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
public class ConstructionServiceTest {

    @Autowired
    private ConstructionService constructionService;
    private List<Construction> constructionList = new ArrayList<>();
    private Construction construction = new Construction("Producent A", "Model X", Construction.roofType.DACH_PLASKI, Construction.roofMaterial.PAPA, 150, 25);

    @Before
    public void setUp() throws Exception {
        constructionService.saveConstruction(construction);
    }

    @After
    public void tearDown() throws Exception {
        constructionList.clear();
        if (constructionService.getAllConstructions().contains(construction)) {
            constructionService.delete(construction.getId());
        }
    }

    @Test
    public void getAllConstructions() {
        //given
        //when
        constructionList = constructionService.getAllConstructions();
        //then
        Assert.assertNotNull(constructionList);
    }

    @Test
    public void getAllIdOrder() {
        //given
        //when
        constructionList = constructionService.getAllIdOrder();
        //then
        Assert.assertNotNull(constructionList);
    }

    @Test
    public void saveConstruction() {
        //given
        Construction testConstr = new Construction("Producent Z", "Model B", Construction.roofType.DACH_SKOSNY, Construction.roofMaterial.BLACHODACHOWKA, 150, 25);
        //when
        constructionService.saveConstruction(testConstr);
        //then
        Assert.assertTrue(constructionService.getAllConstructions().contains(testConstr));
    }

    @Test
    public void delete() {
        //given
        //when
        constructionService.delete(construction.getId());
        //then
        Assert.assertFalse(constructionService.getAllConstructions().contains(construction));
    }

    @Test
    public void getConstruction() {
        //given
        //when
        //then
        Assert.assertNotNull(constructionService.getConstruction(construction.getId()));
    }

    @Test
    public void update() {
        //given
        construction.setRoofmaterial(Construction.roofMaterial.PLYTA_WARSTWOWA);
        //when
        constructionService.update(construction);
        int id = construction.getId();
        //then
        Assert.assertEquals(Construction.roofMaterial.PLYTA_WARSTWOWA, constructionService.getConstruction(id).getRoofmaterial());
    }

    @Test
    public void getConstructionByRoofTypeMaterial() {
        //given
        String roofType = construction.getRooftype().toString();
        String roofMaterial = construction.getRoofmaterial().toString();
        //when
        //then
        Assert.assertNotNull(constructionService.getConstructionByRoofTypeMaterial(roofType,roofMaterial));
    }

    @Test
    public void saveFile() {
    }
}