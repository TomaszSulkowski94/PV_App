package com.pvapp.PVApp.Services;


import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.DBRepositories.QuestionFormDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionFormService {
    @Autowired
    QuestionFormDBRepo questionFormDBRepo;

    @Autowired
    InstalationService instalationService;

    public void create(QuestionForm questionForm) {
        questionFormDBRepo.create(questionForm);
    }


    public void createQuestionForm(QuestionForm questionForm) {
        questionFormDBRepo.create(questionForm);
        instalationService.createInstalation(questionForm);
    }

    public void deleteQuestionForm(int id) {
        questionFormDBRepo.delete(id);

    }

    public void updateQuestionForm(QuestionForm questionForm) {
        questionFormDBRepo.update(questionForm);
    }

    public QuestionForm getQuestionForm(int id) {
        return questionFormDBRepo.printbyid(id);
    }

    public List<QuestionForm> getForms() {
        return new ArrayList<QuestionForm>(questionFormDBRepo.printAll());
    }


}
