package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Construction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest works with JPARepository (in my case I am using manually created repo)
public class ConstructionDBRepoTest {

    @Autowired
    private ConstructionDBRepo constructionDBRepo;

    private Construction construction = new Construction("Producent A", "Model X", Construction.roofType.DACH_PLASKI, Construction.roofMaterial.PAPA, 150, 25);

    @Before
    public void setUp() {
        constructionDBRepo.create(construction);
    }

    @After
    public void clear() {
        if (constructionDBRepo.printAll().contains(construction)) {
            constructionDBRepo.delete(construction.getId());
        }
    }


    @Test
    public void testCreateConstruction() {
        Construction construction1 = new Construction("Producent X", "Model Z", Construction.roofType.DACH_SKOSNY, Construction.roofMaterial.BLACHODACHOWKA, 150, 25);
        constructionDBRepo.create(construction1);
        Assert.assertNotNull(constructionDBRepo.printbyid(construction1.getId()));
    }

    @Test
    public void testGetByid() {
        //given
        //when
        int id = construction.getId();
        //then
        Assert.assertNotNull(constructionDBRepo.printbyid(id));
    }

    @Test
    public void testDeleteConstruction() {
        //given
        int id = construction.getId();
        //when
        constructionDBRepo.delete(id);
        //then
        Assert.assertNull(constructionDBRepo.printbyid(id));
    }

    @Test
    public void testUpdateConstruction() {
        //given
        construction = constructionDBRepo.printbyid(construction.getId());
        //when
        construction.setId(2);
        //then
        constructionDBRepo.update(construction);
        Assert.assertNotNull(constructionDBRepo.printbyid(2));
        Assert.assertEquals(constructionDBRepo.printbyid(2).getPrice(), 150, 0.001);
    }

    @Test
    public void testPrintAllConstruction() {
        //given
        //when
        //then
        Assert.assertNotNull(constructionDBRepo.printAll());
    }

    @Test
    public void testPrintByRooftypeRoofMaterial() {
        //given
        //when
        String dachPlaski = construction.getRooftype().toString();
        String papa = construction.getRoofmaterial().toString();
        //then
        Assert.assertNotNull(constructionDBRepo.getByRoofTypeMaterial(dachPlaski, papa));
    }

    @Test
    public void testPrintByRooftypeWrongRoofMaterial() {
        //given
        //when
        construction.setRooftype(Construction.roofType.DACH_SKOSNY);
        String dachPlaski = construction.getRooftype().toString();
        String changedRoofMaterial = construction.getRoofmaterial().toString();
        //then expected result changed roof material will be replaced by existing one -> in this case from PAPA to BLACHOTRAPEZ
        Assert.assertNotNull(constructionDBRepo.getByRoofTypeMaterial(dachPlaski, changedRoofMaterial));
    }

    @Test
    public void testGetByManufacturerA() {
        //given
        //when
        //then
        Assert.assertNotNull(constructionDBRepo.getByManufactuerA());
    }
}
