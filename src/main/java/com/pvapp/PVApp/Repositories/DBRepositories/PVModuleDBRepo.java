package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.util.Collection;

@Slf4j
@Repository
public class PVModuleDBRepo implements CRUD<PVModule> {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void update(PVModule pvModule) {
        log.info("Merging pv module --repository");
        em.merge(pvModule);
    }

    @Override
    @Transactional
    public void create(PVModule pvModule) {
        log.info("Saving pv module --repository");
        em.persist(pvModule);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting pv module by id --repository");
        em.remove(em.find(PVModule.class, id));
    }

    @Override
    public Collection<PVModule> printAll() {
        log.info("Getting all pv modules order by manufacturer, model, type --repository");
        return em.createQuery("from PVModule order by manufacturer, model, type", PVModule.class).getResultList();
    }

    @Override
    public PVModule printbyid(int id) {
        log.info("Getting pv module by id --repository " + id);
        return em.find(PVModule.class, id);
    }


}
