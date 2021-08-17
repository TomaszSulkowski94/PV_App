package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Slf4j
@Repository
public class QuestionFormDBRepo implements CRUD<QuestionForm> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(QuestionForm questionForm) {
        log.info("Merging question form --repository");
        em.merge(questionForm);
    }

    @Override
    @Transactional
    public void create(QuestionForm questionForm) {
        log.info("Persisting question form --repository");
        em.persist(questionForm);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting question form by id --repository " + id);
        em.remove(em.find(QuestionForm.class, id));
    }

    @Override
    public Collection<QuestionForm> printAll() {
        log.info("Getting all question forms --repository");
        return em.createQuery("FROM QuestionForm", QuestionForm.class).getResultList();
    }

    @Override
    public QuestionForm printbyid(int id) {
        log.info("Getting question form by id --repository " + id);
        return em.find(QuestionForm.class, id);
    }

}
