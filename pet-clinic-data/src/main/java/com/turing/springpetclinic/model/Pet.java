package com.turing.springpetclinic.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/02/14.
 */

@Data
public class Pet {

    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
