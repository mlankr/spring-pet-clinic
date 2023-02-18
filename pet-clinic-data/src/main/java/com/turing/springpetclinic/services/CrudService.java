package com.turing.springpetclinic.services;

import java.util.Set;

/**
 * Created by Milan on 2023/02/16.
 */

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
