package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
public class Pet extends BaseEntity {

    private final String name;
    private final PetType petType;
    private final Owner owner;
    private final LocalDate birthDate;

    @Builder(toBuilder = true)
    public Pet(@Builder.ObtainVia(method = "getId") Long id, String name, PetType petType, Owner owner,
               LocalDate birthDate) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
    }
}
