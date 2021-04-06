package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class OwnerRepoDB implements CRUD<Owner> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(Owner owner) {
        em.merge(owner);
    }

    @Override
    @Transactional
    public void create(Owner owner) {
        em.persist(owner);
    }


    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Owner.class, id));
    }

    @Override
    public Collection<Owner> printAll() {
        return em.createQuery("FROM Owner", Owner.class).getResultList();
    }

    @Override
    public Owner printbyid(int id) {
        return em.find(Owner.class, id);
    }
}
