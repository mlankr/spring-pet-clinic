package com.turing.springpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/04/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

}
