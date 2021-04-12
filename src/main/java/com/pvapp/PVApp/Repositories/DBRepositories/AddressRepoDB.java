package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Address;
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
public class AddressRepoDB implements CRUD<Address> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(Address address) {
        log.info("Merging address --repository");
        em.merge(address);
    }

    @Override
    @Transactional
    public void create(Address address) {
        log.info("Persisting address --repository");
        em.persist(address);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting address by id --repository" + id);
        em.remove(em.find(Address.class, id));
    }

    @Override
    public Collection<Address> printAll() {
        log.info("Getting all addresses --repository");
        return em.createQuery("FROM Address", Address.class).getResultList();
    }

    @Override
    public Address printbyid(int id) {
        log.info("Getting address by id --repository " + id);
        return em.find(Address.class, id);
    }
}
