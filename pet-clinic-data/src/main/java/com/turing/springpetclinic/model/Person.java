package com.turing.springpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Milan on 2023/02/14.
 */

@AllArgsConstructor
@Getter
public class Person extends BaseEntity {

    private String firstname;
    private String lastname;
}
