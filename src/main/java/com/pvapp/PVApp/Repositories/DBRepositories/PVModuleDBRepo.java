package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class PVModuleDBRepo implements CRUD<PVModule> {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void update(PVModule pvModule) {
        em.merge(pvModule);
    }

    @Override
    @Transactional
    public void create(PVModule pvModule) {
        em.persist(pvModule);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(PVModule.class, id));

    }

    @Override
    public Collection<PVModule> printAll() {
        return em.createQuery("from PVModule order by manufacturer, model, type", PVModule.class).getResultList();
    }

    @Override
    public PVModule printbyid(int id) {
        return em.find(PVModule.class, id);
    }


}
