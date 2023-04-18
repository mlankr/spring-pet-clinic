package com.turing.springpetclinic.services;

import com.turing.springpetclinic.model.PetType;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/04/18.
 */
@Service
public interface PetTypeService extends CrudService<PetType, Long> {
}
