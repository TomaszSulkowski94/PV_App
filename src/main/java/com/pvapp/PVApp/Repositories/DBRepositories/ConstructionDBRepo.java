package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Domain.Construction;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class ConstructionDBRepo implements CRUD<Construction> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void update(int id, Construction construction) {

    }

    @Override
    public void create(Construction construction) {
        em.persist(construction);
    }


    @Override
    @Transactional
    public void delete(int id) {
        em.remove(id);
    }

    @Override
    public Collection<Construction> printAll() {
        return em.createQuery("from Construction", Construction.class).getResultList();
    }

    @Override
    public Construction printbyid(int id) {
        return em.find(Construction.class, id);
    }

}
