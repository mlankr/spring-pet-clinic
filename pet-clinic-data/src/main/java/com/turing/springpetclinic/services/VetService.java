package com.turing.springpetclinic.services;

import com.turing.springpetclinic.model.Vet;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
