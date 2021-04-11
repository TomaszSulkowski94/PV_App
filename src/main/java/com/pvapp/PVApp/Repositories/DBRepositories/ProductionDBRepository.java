package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Production;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class ProductionDBRepository implements CRUD<Production> {

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public void update(Production production) {
        em.merge(production);
    }

    @Override
    @Transactional
    public void create(Production production) {
        em.persist(production);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Production.class, id));
    }

    @Override
    public Collection<Production> printAll() {
        return em.createQuery("FROM Production", Production.class).getResultList();
    }

    @Override
    public Production printbyid(int id) {
        return em.find(Production.class, id);
    }


}
