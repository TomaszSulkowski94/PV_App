package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class QuestionFormDBRepo implements CRUD<QuestionForm> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(QuestionForm questionForm) {
        em.merge(questionForm);
    }

    @Override
    @Transactional
    public void create(QuestionForm questionForm) {
        em.persist(questionForm);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(QuestionForm.class, id));
    }

    @Override
    public Collection<QuestionForm> printAll() {
       return em.createQuery("FROM QuestionForm", QuestionForm.class).getResultList();
    }

    @Override
    public QuestionForm printbyid(int id) {
        return em.find(QuestionForm.class,id);
    }
}
