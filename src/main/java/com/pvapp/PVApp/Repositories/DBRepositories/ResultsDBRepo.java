package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Results;
import com.pvapp.PVApp.Repositories.CRUD;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ResultsDBRepo implements CRUD<Results> {
    @Override
    public void update(Results results) {

    }

    @Override
    public void create(Results results) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Collection<Results> printAll() {
        return null;
    }

    @Override
    public Results printbyid(int id) {
        return null;
    }
}
