package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.QuestionForm;
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
public class QuestionFormDBRepoTest {

    @Autowired
    QuestionFormDBRepo questionFormDBRepo;

    private QuestionForm questionForm = new QuestionForm(200, QuestionForm.RoofType.DACH_SKOSNY, 20, 15, QuestionForm.RoofMaterial.BLACHODACHOWKA);
    private List<QuestionForm> questionFormList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        questionFormDBRepo.create(questionForm);
    }

    @After
    public void tearDown() throws Exception {
        questionFormList.clear();
        if (questionFormDBRepo.printAll().contains(questionForm)) {
            questionFormDBRepo.delete(questionForm.getId());
        }
    }

    @Test
    public void update() {
        //given
        questionForm.setRooftype(QuestionForm.RoofType.DACH_PLASKI);
        questionForm.setRoofmaterial(QuestionForm.RoofMaterial.PAPA);
        //when
        questionFormDBRepo.update(questionForm);
        //then
        Assert.assertEquals(QuestionForm.RoofType.DACH_PLASKI, questionFormDBRepo.printbyid(questionForm.getId()).getRooftype());
        Assert.assertEquals(QuestionForm.RoofMaterial.PAPA, questionFormDBRepo.printbyid(questionForm.getId()).getRoofmaterial());
    }

    @Test
    public void create() {
        //given
        QuestionForm qf1 = new QuestionForm(300, QuestionForm.RoofType.DACH_PLASKI, 0, 5, QuestionForm.RoofMaterial.PAPA);
        //when
        questionFormDBRepo.create(qf1);
        //then
        Assert.assertTrue(questionFormDBRepo.printAll().contains(qf1));
    }

    @Test
    public void delete() {
        //given
        //when
        questionFormDBRepo.delete(questionForm.getId());
        //then
        Assert.assertFalse(questionFormDBRepo.printAll().contains(questionForm));
    }

    @Test
    public void printAll() {
        //given
        //when
        //then
        Assert.assertNotNull(questionFormDBRepo.printAll());
    }

    @Test
    public void printbyid() {
        //given
        //when
        //then
        Assert.assertNotNull(questionFormDBRepo.printbyid(questionForm.getId()));
    }

}