package com.pvapp.PVApp.Repositories;


import java.util.Collection;

public interface CRUD<T> {

    void update(T t);

    void create(T t);

    void delete(int id);

    Collection<T> printAll();

    T printbyid(int id);


}
