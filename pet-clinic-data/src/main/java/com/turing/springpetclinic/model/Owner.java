package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */
@Getter
public class Owner extends Person {

    private final String address;
    private final String city;
    private final String telephone;
    private final Set<Pet> pets;

    @Builder(toBuilder = true)
    public Owner(@Builder.ObtainVia(method = "getFirstname") String firstname,
                 @Builder.ObtainVia(method = "getLastname") String lastname, String address, String city,
                 String telephone,
                 @Singular Set<Pet> pets) {
        super(firstname, lastname);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }
}
