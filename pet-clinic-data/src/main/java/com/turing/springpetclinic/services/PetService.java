package com.turing.springpetclinic.services;

import com.turing.springpetclinic.model.Pet;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
