package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */
@Getter
public class Vet extends Person {

    private final Set<Specialty> specialties;

    @Builder(toBuilder = true)
    public Vet(@Builder.ObtainVia(method = "getFirstname") String firstname,
               @Builder.ObtainVia(method = "getLastname") String lastname,
               @Singular Set<Specialty> specialties) {
        super(firstname, lastname);
        this.specialties = specialties;
    }
}
