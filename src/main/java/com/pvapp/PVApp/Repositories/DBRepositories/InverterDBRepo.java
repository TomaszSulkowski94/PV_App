package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class InverterDBRepo implements CRUD<Inverter> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void update(Inverter inverter) {
        log.info("Merging inverter --repository");
        em.merge(inverter);
    }

    @Override
    @Transactional
    public void create(Inverter inverter) {
        log.info("Persisting inverter --repository");
        em.persist(inverter);
    }

    @Transactional
    public void saveAll(List<Inverter> inverterList) {
        log.info("Persisting inverters from lists --repository");
        for (Inverter inverter : inverterList) {
            em.persist(inverter);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting inverter by id--repository " + id);
        em.remove(em.find(Inverter.class, id));
    }

    @Override
    public Collection<Inverter> printAll() {
        log.info("Getting all inverters ordered by acpower--repository");
        return em.createQuery("from Inverter order by acpower", Inverter.class).getResultList();
    }

    @Override
    public Inverter printbyid(int id) {
        log.info("Getting inverter by id --repository" + id);
        return em.find(Inverter.class, id);
    }

}
