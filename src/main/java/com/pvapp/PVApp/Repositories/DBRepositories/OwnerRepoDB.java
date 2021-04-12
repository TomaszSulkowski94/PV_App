package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Slf4j
@Repository
public class OwnerRepoDB implements CRUD<Owner> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(Owner owner) {
        log.info("Merging owner --repository");
        em.merge(owner);
    }

    @Override
    @Transactional
    public void create(Owner owner) {
        log.info("Persisting owner --repository");
        em.persist(owner);
    }


    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting owner by id --repository " + id);
        em.remove(em.find(Owner.class, id));
    }

    @Override
    public Collection<Owner> printAll() {
        log.info("Getting all owners --repository");
        return em.createQuery("FROM Owner", Owner.class).getResultList();
    }

    @Override
    public Owner printbyid(int id) {
        log.info("Getting owner by id --repository " + id);
        return em.find(Owner.class, id);
    }
}
