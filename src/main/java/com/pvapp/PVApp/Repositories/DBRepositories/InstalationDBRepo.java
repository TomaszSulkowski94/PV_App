package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class InstalationDBRepo implements CRUD<Instalation> {
    @PersistenceContext
    EntityManager em;

    @Override
    public void update(Instalation instalation) {

    }

    @Override
    @Transactional
    public void create(Instalation instalation) {
        em.persist(instalation);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Instalation.class, id));
    }


    @Override
    public Collection<Instalation> printAll() {
        return em.createQuery(" from Instalation", Instalation.class).getResultList();
    }

    @Override
    public Instalation printbyid(int id) {
        return null;
    }
}
