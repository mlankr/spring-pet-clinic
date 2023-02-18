package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by Milan on 2023/02/18.
 */

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet.getId(), vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}