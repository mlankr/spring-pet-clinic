package com.turing.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@Setter
public class Vet extends Person {

    private Set<Specialty> specialties;
}
