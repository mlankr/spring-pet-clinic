package com.turing.springpetclinic.services;

import com.turing.springpetclinic.model.Visit;
import java.util.Set;

/**
 * Created by Milan on 2023/05/09.
 */
public interface VisitService extends CrudService<Visit, Long> {

	Set<Visit> findVisitsByPet(long petId);
}
