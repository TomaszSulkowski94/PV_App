package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class InstalationDBRepo implements CRUD<Instalation> {
    @PersistenceContext
    EntityManager em;

    @Override
    public void update(Instalation instalationDBRepo) {

    }

    @Override
    public void create(Instalation instalationDBRepo) {

    }

    @Override
    public void delete(int id) {

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
