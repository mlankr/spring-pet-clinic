package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by Milan on 2023/02/18.
 */

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet pet) {
        super.delete(pet);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(), pet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}