package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.*;

import com.pvapp.PVApp.Repositories.DBRepositories.ProductionDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductionService {

    @Autowired
    ProductionDBRepository productionDBRepository;

    @Autowired
    QuestionFormService questionFormService;

    @Autowired
    ConstructionService constructionService;

    @Autowired
    PVModuleService pvModuleService;

    private void calcProduction(Instalation instalation, Production production) {
        log.info("Calculating production  --service");
        PVModule pvModule = pvModuleService.getPVModule(instalation.getPvModule().getId());
        double[] productionfactors = {0.03, 0.04, 0.07, 0.09, 0.12, 0.13, 0.12, 0.13, 0.11, 0.09, 0.05, 0.03};
        double[] productionArray = new double[12];
        double electricFactor = electricFactor(questionFormService.getQuestionForm(instalation.getQuestionForm().getId()), instalation);
        double summary = 0;
        for (int i = 0; i < productionfactors.length; i++) {
            double calcProduction = (instalation.getPower() / 1000) * productionfactors[i] * electricFactor * 1025 * (1 - pvModule.getTemperatureLost());
            calcProduction = roundResult(calcProduction);
            productionArray[i] = calcProduction;
            log.info("Calculating production for " + (i + 1) + " month: " + calcProduction + " kWh");
            summary += calcProduction;
        }
        log.info("Summary production: " + summary + " kWh");
        production.setJanuary(productionArray[0]);
        production.setFebruary(productionArray[1]);
        production.setMarch(productionArray[2]);
        production.setApril(productionArray[3]);
        production.setMay(productionArray[4]);
        production.setJune(productionArray[5]);
        production.setJuly(productionArray[6]);
        production.setAugust(productionArray[7]);
        production.setSeptember(productionArray[8]);
        production.setOctober(productionArray[9]);
        production.setNovember(productionArray[10]);
        production.setDecember(productionArray[11]);
        production.setSummary(summary);
    }

    public void createProduction(Instalation instalation) {
        log.info("Saving production --service");
        Production production = new Production();
        calcProduction(instalation, production);
        productionDBRepository.create(production);
        instalation.setProduction(production);
    }

    private double roundResult(double number) {
        number = Math.round(number * 100);
        return number / 100;
    }


    public void updateProduction(Instalation instalation) {
        log.info("Updating production --service");
        Production production = getProduction(instalation.getProduction().getId());
        calcProduction(instalation, production);
        instalation.setProduction(production);
        productionDBRepository.update(production);
    }

    public void deleteProduction(int id) {
        log.info("Deleting production --service" + id);
        productionDBRepository.delete(id);
    }

    public Production getProduction(int id) {
        log.info("Getting production by id --service" + id);
        return productionDBRepository.printbyid(id);
    }

    public List<Production> getProduction() {
        return new ArrayList<Production>(productionDBRepository.printAll());
    }


    private double electricFactor(QuestionForm questionForm, Instalation instalation) {
        int roofslope = 0;
        int roofposition = (instalation.getRoofposition() / 5) + 1;
        Construction construction = constructionService.getConstruction(instalation.getConstruction().getId());
        double[][] factorTable = createArrayFactors();
        if (instalation.getConstruction().getRoofslope() == 0) {
            roofslope = (instalation.getInstalationangle() / 5) + 1;
        } else {
            roofslope = (instalation.getInstalationangle() / 5) + 1;
        }
        log.info("Matched coefficent: " + factorTable[roofslope][roofposition] + " for roofslope: " + roofslope + " and roofposition: " + roofposition);
        return factorTable[roofslope][roofposition];


    }

    private double[][] createArrayFactors() {
        double[][] matrixTable = new double[20][20];
        //[i][j]
        //kolumna 1
        matrixTable[0][0] = 0;
        matrixTable[1][0] = 0;
        matrixTable[2][0] = 5;
        matrixTable[3][0] = 10;
        matrixTable[4][0] = 15;
        matrixTable[5][0] = 20;
        matrixTable[6][0] = 25;
        matrixTable[7][0] = 30;
        matrixTable[8][0] = 35;
        matrixTable[9][0] = 40;
        matrixTable[10][0] = 45;
        matrixTable[11][0] = 50;
        matrixTable[12][0] = 55;
        matrixTable[13][0] = 60;
        matrixTable[14][0] = 65;
        matrixTable[15][0] = 70;
        matrixTable[16][0] = 75;
        matrixTable[17][0] = 80;
        matrixTable[18][0] = 85;
        matrixTable[19][0] = 90;
//kolumna 20
        matrixTable[0][19] = 90;
        matrixTable[1][19] = 1.00;
        matrixTable[2][19] = 1.00;
        matrixTable[3][19] = 0.99;
        matrixTable[4][19] = 0.98;
        matrixTable[5][19] = 0.97;
        matrixTable[6][19] = 0.96;
        matrixTable[7][19] = 0.94;
        matrixTable[8][19] = 0.93;
        matrixTable[9][19] = 0.91;
        matrixTable[10][19] = 0.88;
        matrixTable[11][19] = 0.87;
        matrixTable[12][19] = 0.85;
        matrixTable[13][19] = 0.82;
        matrixTable[14][19] = 0.80;
        matrixTable[15][19] = 0.77;
        matrixTable[16][19] = 0.74;
        matrixTable[17][19] = 0.71;
        matrixTable[18][19] = 0.67;
        matrixTable[19][19] = 0.64;
// kolumna 19

        matrixTable[0][18] = 85;
        matrixTable[1][18] = 1.00;
        matrixTable[2][18] = 1.00;
        matrixTable[3][18] = 0.99;
        matrixTable[4][18] = 0.98;
        matrixTable[5][18] = 0.97;
        matrixTable[6][18] = 0.96;
        matrixTable[7][18] = 0.95;
        matrixTable[8][18] = 0.93;
        matrixTable[9][18] = 0.91;
        matrixTable[10][18] = 0.89;
        matrixTable[11][18] = 0.87;
        matrixTable[12][18] = 0.85;
        matrixTable[13][18] = 0.82;
        matrixTable[14][18] = 0.80;
        matrixTable[15][18] = 0.79;
        matrixTable[16][18] = 0.79;
        matrixTable[17][18] = 0.73;
        matrixTable[18][18] = 0.69;
        matrixTable[19][18] = 0.66;
        //kolumna 18

        matrixTable[0][17] = 80;
        matrixTable[1][17] = 1.00;
        matrixTable[2][17] = 1.00;
        matrixTable[3][17] = 1.01;
        matrixTable[4][17] = 1.00;
        matrixTable[5][17] = 1.00;
        matrixTable[6][17] = 0.99;
        matrixTable[7][17] = 0.98;
        matrixTable[8][17] = 0.97;
        matrixTable[9][17] = 0.95;
        matrixTable[10][17] = 0.93;
        matrixTable[11][17] = 0.92;
        matrixTable[12][17] = 0.89;
        matrixTable[13][17] = 0.87;
        matrixTable[14][17] = 0.84;
        matrixTable[15][17] = 0.81;
        matrixTable[16][17] = 0.78;
        matrixTable[17][17] = 0.75;
        matrixTable[18][17] = 0.71;
        matrixTable[19][17] = 0.68;
//kolumna 17
        matrixTable[0][16] = 75;
        matrixTable[1][16] = 1.00;
        matrixTable[2][16] = 1.01;
        matrixTable[3][16] = 1.01;
        matrixTable[4][16] = 1.01;
        matrixTable[5][16] = 1.01;
        matrixTable[6][16] = 1.00;
        matrixTable[7][16] = 1.00;
        matrixTable[8][16] = 0.99;
        matrixTable[9][16] = 0.97;
        matrixTable[10][16] = 0.96;
        matrixTable[11][16] = 0.94;
        matrixTable[12][16] = 0.92;
        matrixTable[13][16] = 0.89;
        matrixTable[14][16] = 0.86;
        matrixTable[15][16] = 0.83;
        matrixTable[16][16] = 0.80;
        matrixTable[17][16] = 0.77;
        matrixTable[18][16] = 0.73;
        matrixTable[19][16] = 0.69;

        //kolumna 16
        matrixTable[0][15] = 70;
        matrixTable[1][15] = 1.00;
        matrixTable[2][15] = 1.01;
        matrixTable[3][15] = 1.02;
        matrixTable[4][15] = 1.02;
        matrixTable[5][15] = 1.02;
        matrixTable[6][15] = 1.02;
        matrixTable[7][15] = 1.01;
        matrixTable[8][15] = 1.00;
        matrixTable[9][15] = 0.99;
        matrixTable[10][15] = 0.98;
        matrixTable[11][15] = 0.96;
        matrixTable[12][15] = 0.94;
        matrixTable[13][15] = 0.91;
        matrixTable[14][15] = 0.88;
        matrixTable[15][15] = 0.85;
        matrixTable[16][15] = 0.82;
        matrixTable[17][15] = 0.79;
        matrixTable[18][15] = 0.75;
        matrixTable[19][15] = 0.71;

        //kolumna 15
        matrixTable[0][14] = 65;
        matrixTable[1][14] = 1.00;
        matrixTable[2][14] = 1.01;
        matrixTable[3][14] = 1.02;
        matrixTable[4][14] = 1.03;
        matrixTable[5][14] = 1.03;
        matrixTable[6][14] = 1.03;
        matrixTable[7][14] = 1.03;
        matrixTable[8][14] = 1.02;
        matrixTable[9][14] = 1.01;
        matrixTable[10][14] = 1.00;
        matrixTable[11][14] = 0.98;
        matrixTable[12][14] = 0.96;
        matrixTable[13][14] = 0.93;
        matrixTable[14][14] = 0.90;
        matrixTable[15][14] = 0.87;
        matrixTable[16][14] = 0.84;
        matrixTable[17][14] = 0.80;
        matrixTable[18][14] = 0.77;
        matrixTable[19][14] = 0.72;

        //kolumna 14
        matrixTable[0][13] = 60;
        matrixTable[1][13] = 1.00;
        matrixTable[2][13] = 1.02;
        matrixTable[3][13] = 1.03;
        matrixTable[4][13] = 1.04;
        matrixTable[5][13] = 1.04;
        matrixTable[6][13] = 1.05;
        matrixTable[7][13] = 1.04;
        matrixTable[8][13] = 1.04;
        matrixTable[9][13] = 1.03;
        matrixTable[10][13] = 1.01;
        matrixTable[11][13] = 1.00;
        matrixTable[12][13] = 0.97;
        matrixTable[13][13] = 0.95;
        matrixTable[14][13] = 0.92;
        matrixTable[15][13] = 0.89;
        matrixTable[16][13] = 0.86;
        matrixTable[17][13] = 0.82;
        matrixTable[18][13] = 0.78;
        matrixTable[19][13] = 0.74;

        //kolumna 13
        matrixTable[0][12] = 55;
        matrixTable[1][12] = 1;
        matrixTable[2][12] = 1.02;
        matrixTable[3][12] = 1.04;
        matrixTable[4][12] = 1.05;
        matrixTable[5][12] = 1.05;
        matrixTable[6][12] = 1.06;
        matrixTable[7][12] = 1.06;
        matrixTable[8][12] = 1.05;
        matrixTable[9][12] = 1.04;
        matrixTable[10][12] = 1.03;
        matrixTable[11][12] = 1.01;
        matrixTable[12][12] = 0.99;
        matrixTable[13][12] = 1;
        matrixTable[14][12] = 0.94;
        matrixTable[15][12] = 0.91;
        matrixTable[16][12] = 0.87;
        matrixTable[17][12] = 0.83;
        matrixTable[18][12] = 0.79;
        matrixTable[19][12] = 0.75;

        //kolumna 12
        matrixTable[0][11] = 50;
        matrixTable[1][11] = 1;
        matrixTable[2][11] = 1.02;
        matrixTable[3][11] = 1.04;
        matrixTable[4][11] = 1.05;
        matrixTable[5][11] = 1.06;
        matrixTable[6][11] = 1.07;
        matrixTable[7][11] = 1.07;
        matrixTable[8][11] = 1.07;
        matrixTable[9][11] = 1.06;
        matrixTable[10][11] = 1.05;
        matrixTable[11][11] = 1.03;
        matrixTable[12][11] = 1.01;
        matrixTable[13][11] = 0.98;
        matrixTable[14][11] = 0.95;
        matrixTable[15][11] = 0.92;
        matrixTable[16][11] = 0.89;
        matrixTable[17][11] = 0.85;
        matrixTable[18][11] = 0.81;
        matrixTable[19][11] = 0.76;

        //kolumna 11
        matrixTable[0][10] = 45;
        matrixTable[1][10] = 1;
        matrixTable[2][10] = 1.03;
        matrixTable[3][10] = 1.05;
        matrixTable[4][10] = 1.06;
        matrixTable[5][10] = 1.07;
        matrixTable[6][10] = 1.08;
        matrixTable[7][10] = 1.08;
        matrixTable[8][10] = 1.08;
        matrixTable[9][10] = 1.07;
        matrixTable[10][10] = 1.06;
        matrixTable[11][10] = 1.04;
        matrixTable[12][10] = 1.02;
        matrixTable[13][10] = 1;
        matrixTable[14][10] = 0.97;
        matrixTable[15][10] = 0.93;
        matrixTable[16][10] = 0.90;
        matrixTable[17][10] = 0.86;
        matrixTable[18][10] = 0.82;
        matrixTable[19][10] = 0.77;

        //kolumna 10
        matrixTable[0][9] = 40;
        matrixTable[1][9] = 1;
        matrixTable[2][9] = 1.03;
        matrixTable[3][9] = 1.05;
        matrixTable[4][9] = 1.07;
        matrixTable[5][9] = 1.08;
        matrixTable[6][9] = 1.09;
        matrixTable[7][9] = 1.09;
        matrixTable[8][9] = 1.09;
        matrixTable[9][9] = 1.09;
        matrixTable[10][9] = 1.07;
        matrixTable[11][9] = 1.06;
        matrixTable[12][9] = 1.04;
        matrixTable[13][9] = 1.01;
        matrixTable[14][9] = 0.98;
        matrixTable[15][9] = 0.95;
        matrixTable[16][9] = 0.91;
        matrixTable[17][9] = 0.87;
        matrixTable[18][9] = 0.83;
        matrixTable[19][9] = 0.78;

        //kolumna 9
        matrixTable[0][8] = 35;
        matrixTable[1][8] = 1.00;
        matrixTable[2][8] = 1.03;
        matrixTable[3][8] = 1.05;
        matrixTable[4][8] = 1.07;
        matrixTable[5][8] = 1.09;
        matrixTable[6][8] = 1.10;
        matrixTable[7][8] = 1.10;
        matrixTable[8][8] = 1.10;
        matrixTable[9][8] = 1.10;
        matrixTable[10][8] = 1.09;
        matrixTable[11][8] = 1.07;
        matrixTable[12][8] = 1.05;
        matrixTable[13][8] = 1.02;
        matrixTable[14][8] = 0.99;
        matrixTable[15][8] = 0.96;
        matrixTable[16][8] = 0.92;
        matrixTable[17][8] = 0.88;
        matrixTable[18][8] = 0.83;
        matrixTable[19][8] = 0.79;

        //kolumna 8
        matrixTable[0][7] = 30;
        matrixTable[1][7] = 1.00;
        matrixTable[2][7] = 1.03;
        matrixTable[3][7] = 1.06;
        matrixTable[4][7] = 1.08;
        matrixTable[5][7] = 1.09;
        matrixTable[6][7] = 1.10;
        matrixTable[7][7] = 1.11;
        matrixTable[8][7] = 1.11;
        matrixTable[9][7] = 1.10;
        matrixTable[10][7] = 1.10;
        matrixTable[11][7] = 1.08;
        matrixTable[12][7] = 1.06;
        matrixTable[13][7] = 1.03;
        matrixTable[14][7] = 1.00;
        matrixTable[15][7] = 0.97;
        matrixTable[16][7] = 0.93;
        matrixTable[17][7] = 0.89;
        matrixTable[18][7] = 0.84;
        matrixTable[19][7] = 0.79;

        //kolumna 7
        matrixTable[0][6] = 25;
        matrixTable[1][6] = 1.00;
        matrixTable[2][6] = 1.03;
        matrixTable[3][6] = 1.06;
        matrixTable[4][6] = 1.08;
        matrixTable[5][6] = 1.10;
        matrixTable[6][6] = 1.11;
        matrixTable[7][6] = 1.12;
        matrixTable[8][6] = 1.12;
        matrixTable[9][6] = 1.11;
        matrixTable[10][6] = 1.10;
        matrixTable[11][6] = 1.09;
        matrixTable[12][6] = 1.07;
        matrixTable[13][6] = 1.04;
        matrixTable[14][6] = 1.01;
        matrixTable[15][6] = 0.97;
        matrixTable[16][6] = 0.93;
        matrixTable[17][6] = 0.89;
        matrixTable[18][6] = 0.84;
        matrixTable[19][6] = 0.79;

        //kolumna 6
        matrixTable[0][5] = 20;
        matrixTable[1][5] = 1.00;
        matrixTable[2][5] = 1.03;
        matrixTable[3][5] = 1.06;
        matrixTable[4][5] = 1.09;
        matrixTable[5][5] = 1.11;
        matrixTable[6][5] = 1.12;
        matrixTable[7][5] = 1.13;
        matrixTable[8][5] = 1.13;
        matrixTable[9][5] = 1.12;
        matrixTable[10][5] = 1.11;
        matrixTable[11][5] = 1.10;
        matrixTable[12][5] = 1.08;
        matrixTable[13][5] = 1.05;
        matrixTable[14][5] = 1.02;
        matrixTable[15][5] = 0.98;
        matrixTable[16][5] = 0.94;
        matrixTable[17][5] = 0.90;
        matrixTable[18][5] = 0.85;
        matrixTable[19][5] = 0.80;

        //kolumna 5
        matrixTable[0][4] = 15;
        matrixTable[1][4] = 1.00;
        matrixTable[2][4] = 1.03;
        matrixTable[3][4] = 1.06;
        matrixTable[4][4] = 1.09;
        matrixTable[5][4] = 1.11;
        matrixTable[6][4] = 1.12;
        matrixTable[7][4] = 1.13;
        matrixTable[8][4] = 1.13;
        matrixTable[9][4] = 1.12;
        matrixTable[10][4] = 1.11;
        matrixTable[11][4] = 1.10;
        matrixTable[12][4] = 1.08;
        matrixTable[13][4] = 1.05;
        matrixTable[14][4] = 1.02;
        matrixTable[15][4] = 0.98;
        matrixTable[16][4] = 0.94;
        matrixTable[17][4] = 0.90;
        matrixTable[18][4] = 0.85;
        matrixTable[19][4] = 0.80;

        //kolumna 4
        matrixTable[0][3] = 10;
        matrixTable[1][3] = 1.00;
        matrixTable[2][3] = 1.04;
        matrixTable[3][3] = 1.07;
        matrixTable[4][3] = 1.09;
        matrixTable[5][3] = 1.11;
        matrixTable[6][3] = 1.12;
        matrixTable[7][3] = 1.13;
        matrixTable[8][3] = 1.13;
        matrixTable[9][3] = 1.13;
        matrixTable[10][3] = 1.12;
        matrixTable[11][3] = 1.10;
        matrixTable[12][3] = 1.08;
        matrixTable[13][3] = 1.05;
        matrixTable[14][3] = 1.02;
        matrixTable[15][3] = 0.99;
        matrixTable[16][3] = 0.94;
        matrixTable[17][3] = 0.90;
        matrixTable[18][3] = 0.85;
        matrixTable[19][3] = 0.80;

        //kolumna 3
        matrixTable[0][2] = 5;
        matrixTable[1][2] = 1.00;
        matrixTable[2][2] = 1.04;
        matrixTable[3][2] = 1.07;
        matrixTable[4][2] = 1.09;
        matrixTable[5][2] = 1.11;
        matrixTable[6][2] = 1.12;
        matrixTable[7][2] = 1.13;
        matrixTable[8][2] = 1.14;
        matrixTable[9][2] = 1.13;
        matrixTable[10][2] = 1.12;
        matrixTable[11][2] = 1.10;
        matrixTable[12][2] = 1.08;
        matrixTable[13][2] = 1.06;
        matrixTable[14][2] = 1.02;
        matrixTable[15][2] = 0.99;
        matrixTable[16][2] = 0.95;
        matrixTable[17][2] = 0.90;
        matrixTable[18][2] = 0.85;
        matrixTable[19][2] = 0.80;

        //kolumna 2
        matrixTable[0][1] = 0;
        matrixTable[1][1] = 1.00;
        matrixTable[2][1] = 1.04;
        matrixTable[3][1] = 1.07;
        matrixTable[4][1] = 1.10;
        matrixTable[5][1] = 1.01;
        matrixTable[6][1] = 1.12;
        matrixTable[7][1] = 1.14;
        matrixTable[8][1] = 1.15;
        matrixTable[9][1] = 1.14;
        matrixTable[10][1] = 1.12;
        matrixTable[11][1] = 1.11;
        matrixTable[12][1] = 1.08;
        matrixTable[13][1] = 1.06;
        matrixTable[14][1] = 1.02;
        matrixTable[15][1] = 0.99;
        matrixTable[16][1] = 0.95;
        matrixTable[17][1] = 0.90;
        matrixTable[18][1] = 0.85;
        matrixTable[19][1] = 0.80;
        return matrixTable;
    }
}
