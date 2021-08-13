package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.DBRepositories.InstalationDBRepo;
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
public class QuestionFormServiceTest {

    @Autowired
    QuestionFormService questionFormService;

    @Autowired
    InstalationDBRepo instalationDBRepo;

    @Autowired
    PVModuleService pvModuleService;

    private QuestionForm qf2 = new QuestionForm(50, QuestionForm.RoofType.DACH_SKOSNY, 20, 0, QuestionForm.RoofMaterial.BLACHODACHOWKA);
    private final List<QuestionForm> qfList = new ArrayList<>();

    @Before
    public void setUp() {
        questionFormService.createQuestionForm(qf2);
    }

    @After
    public void tearDown() {
        qfList.clear();
        if (questionFormService.getForms().contains(qf2)) {
            questionFormService.deleteQuestionForm(qf2.getId());
        }
    }

    @Test
    public void create() {
        //given
        QuestionForm qf1 = new QuestionForm(150, QuestionForm.RoofType.DACH_SKOSNY, 20, 0, QuestionForm.RoofMaterial.BLACHODACHOWKA);
        //when
        questionFormService.create(qf1);
        //then
        Assert.assertTrue(questionFormService.getForms().contains(qf1));
        Assert.assertNotNull(questionFormService.getQuestionForm(qf1.getId()));
    }

    @Test
    public void createQuestionFormCheck() {
        //given
        QuestionForm qf1 = new QuestionForm(150, QuestionForm.RoofType.DACH_SKOSNY, 20, 0, QuestionForm.RoofMaterial.BLACHODACHOWKA);
        //when
        questionFormService.createQuestionForm(qf1);
        //then
        Assert.assertTrue(questionFormService.getForms().contains(qf1));
        Assert.assertNotNull(questionFormService.getQuestionForm(qf1.getId()));
        Assert.assertNotNull(instalationDBRepo.printbyQFid(qf1));
    }

    @Test
    public void deleteQuestionForm() {
        //given
        //when
        questionFormService.deleteQuestionForm(qf2.getId());
        //then
        Assert.assertNull(questionFormService.getQuestionForm(qf2.getId()));
    }

    @Test
    public void updateQuestionFormByInstalation() {
        //given
        qf2.setBill(200);
        int id = qf2.getId();
        //when
        questionFormService.updateQuestionFormByInstalation(qf2);
        //then
        Assert.assertEquals(200, questionFormService.getQuestionForm(id).getBill());
    }

    @Test
    public void updateQuestionForm() {
        //given
        qf2.setBill(100);
        //when
        questionFormService.updateQuestionForm(qf2);
        Instalation instalation = instalationDBRepo.printbyQFid(qf2);
        //then
        int newBill = questionFormService.getQuestionForm(qf2.getId()).getBill();
        double newPower = instalation.getPower();
        Assert.assertEquals(qf2.getBill(), newBill, 0);
        Assert.assertEquals(2520, newPower, 0);
    }

    @Test
    public void getQuestionForm() {
        //given
        //when
        //then
        Assert.assertNotNull(questionFormService.getQuestionForm(qf2.getId()));
    }

    @Test
    public void getForms() {
        //given
        //when
        //then
        Assert.assertNotNull(questionFormService.getForms());
    }
}