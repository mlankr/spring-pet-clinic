package com.turing.springpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/02/14.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Pet extends BaseEntity {

    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
