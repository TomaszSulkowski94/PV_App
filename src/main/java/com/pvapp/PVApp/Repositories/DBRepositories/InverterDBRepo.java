package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class InverterDBRepo implements CRUD<Inverter> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void update(Inverter inverter) {
        em.merge(inverter);
    }

    @Override
    @Transactional
    public void create(Inverter inverter) {
        em.persist(inverter);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Inverter.class, id));
    }

    @Override
    public Collection<Inverter> printAll() {
        return em.createQuery("from Inverter", Inverter.class).getResultList();
    }

    @Override
    public Inverter printbyid(int id) {
        return em.find(Inverter.class, id);
    }

}
