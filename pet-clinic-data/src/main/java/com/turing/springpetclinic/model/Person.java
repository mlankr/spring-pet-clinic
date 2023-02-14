package com.turing.springpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Milan on 2023/02/14.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
}
