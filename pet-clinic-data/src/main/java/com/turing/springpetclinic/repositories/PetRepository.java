package com.turing.springpetclinic.repositories;

import com.turing.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Milan on 2023/05/08.
 */
public interface PetRepository extends CrudRepository<Pet, Long> {

}