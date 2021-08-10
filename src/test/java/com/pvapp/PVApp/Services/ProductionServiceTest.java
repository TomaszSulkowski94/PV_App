package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProductionServiceTest {

    private Instalation instalationFlatRoof;
    private Instalation instalationAngledRoof;
    private PVModule pvModule;
    private Inverter inverter;
    private Construction constructionFlatRoof;
    private Construction constructionAngleRoof;

    ProductionService productionService;

    @Before
    public void setUp() {
        productionService = new ProductionService();
        pvModule = new PVModule("Producent W", "WP250", PVModule.moduleType.MONOKRYSTALICZNY, 250, 9.22, 8.68, 37.85, 32.26, 0.24, 0.1691, 0.3);
        inverter = new Inverter("Producent S", "STL-X", Inverter.InverterType.TROJFAZOWY, 9100, 8000, 2, 11.00, 14.00, 290, 840, 1000, 4700);
      //  constructionFlatRoof = new Construction("Producent A", "Model B", Construction.roofType.DACH_PLASKI, Construction.roofMaterial.PAPA, 0.25, 15);
      //  constructionAngleRoof = new Construction("Producent A", "Model B", Construction.roofType.DACH_SKOSNY, Construction.roofMaterial.DACHÓWKA_CERAMICZNA, 0.25);
        constructionFlatRoof = new Construction(1,"Producent A", "Model B", Construction.roofType.DACH_PLASKI, Construction.roofMaterial.PAPA, 0.25, 15);
        constructionAngleRoof = new Construction(2,"Producent A", "Model B", Construction.roofType.DACH_SKOSNY, Construction.roofMaterial.DACHÓWKA_CERAMICZNA, 0.25 ,0);
        instalationFlatRoof = new Instalation(pvModule, 20, inverter, 1, constructionFlatRoof, 15, 20);
        instalationAngledRoof = new Instalation(pvModule, 20, inverter, 1, constructionAngleRoof, 25, 20);
    }

    @Test
    public void checkAngleRoofMatrixValue() {
        //Given
        int factor = instalationAngledRoof.getInstalationangle();
        //When
        factor = (factor / 5) + 1;
        //Then
        Assert.assertEquals(6, factor);
    }

    @Test
    public void checkAngleRoofPosition() {
        //Given
        int position = instalationAngledRoof.getRoofposition();
        //When
        position = (position / 5) + 1;
        //Then
        Assert.assertEquals(5, position);
    }


    @Test
    public void checkElectricFactorFlatRoof() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ProductionService.class.getDeclaredMethod("electricFactor", Instalation.class);
        method.setAccessible(true);
        //given
        //when
        double factor = (double) method.invoke(productionService, instalationAngledRoof);
        //then
        Assert.assertEquals(1.12, factor);
    }

    @Test
    public void checkFlatRoofValue() {
        //given
        int position = instalationAngledRoof.getInstalationangle();
        //when
        position = (position / 5) + 1;
        //then
        Assert.assertEquals(6, position);
    }

    @Test
    public void checkFlatRoofPosition() {
        //Given
        int position = instalationFlatRoof.getRoofposition();
        //When
        position = (position / 5) + 1;
        //Then
        Assert.assertEquals(5, position);
    }

}