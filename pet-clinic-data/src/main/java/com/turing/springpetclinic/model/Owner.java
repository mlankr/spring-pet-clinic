package com.turing.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */
@Getter
@Setter
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;
}
