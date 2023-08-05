package com.turing.springpetclinic.repositories;

import com.turing.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Milan on 2023/05/08.
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {

}