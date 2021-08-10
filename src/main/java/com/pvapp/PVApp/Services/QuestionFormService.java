package com.pvapp.PVApp.Services;


import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.DBRepositories.QuestionFormDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionFormService {
    @Autowired
    QuestionFormDBRepo questionFormDBRepo;

    @Autowired
    InstalationService instalationService;

    public void create(QuestionForm questionForm) {
        log.info("Saving questionform --service");
        questionFormDBRepo.create(questionForm);
    }


    public void createQuestionForm(QuestionForm questionForm) {
        log.info("Saving questionform --service");
        questionFormDBRepo.create(questionForm);
        instalationService.createInstalation(questionForm);
    }

    public void deleteQuestionForm(int id) {
        log.info("Deleting questionform by id--service " + id);
        questionFormDBRepo.delete(id);

    }

    public void updateQuestionFormByInstalation(QuestionForm questionForm) {
        log.info("Updating questionform --service");
        questionFormDBRepo.update(questionForm);
    }

    public void updateQuestionForm(QuestionForm questionForm) {
        log.info("Updating questionform --service");
        questionFormDBRepo.update(questionForm);
        instalationService.updateByQuestionForm(questionForm);

    }

    public QuestionForm getQuestionForm(int id) {
        log.info("Getting questionform by id --service " + id);
        return questionFormDBRepo.printbyid(id);
    }

    public List<QuestionForm> getForms() {
        log.info("Getting all questionforms  --service ");
        return new ArrayList<QuestionForm>(questionFormDBRepo.printAll());
    }

    public QuestionForm getFirst() {
        log.info("Getting first questionform --service");
        return questionFormDBRepo.getFirst();
    }


}
