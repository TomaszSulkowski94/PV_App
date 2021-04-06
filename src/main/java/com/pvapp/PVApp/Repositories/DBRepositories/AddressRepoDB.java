package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Address;
import com.pvapp.PVApp.Entities.Owner;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AddressRepoDB implements CRUD<Address> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(Address address) {
        em.merge(address);
    }

    @Override
    @Transactional
    public void create(Address address) {
        em.persist(address);
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Address.class, id));
    }

    @Override
    public Collection<Address> printAll() {
        return em.createQuery("FROM Address", Address.class).getResultList();
    }

    @Override
    public Address printbyid(int id) {
        return em.find(Address.class, id);
    }
}
