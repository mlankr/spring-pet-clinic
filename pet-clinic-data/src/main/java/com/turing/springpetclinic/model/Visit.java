package com.turing.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/04/18.
 */
@Getter
@Setter
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

}
