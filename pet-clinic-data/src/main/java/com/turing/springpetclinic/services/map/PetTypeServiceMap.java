package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Milan on 2023/04/18.
 */

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(PetType object) {

    }

    @Override
    public PetType save(PetType object) {
        return null;
    }

    @Override
    public PetType findById(Long id) {
        return null;
    }
}
