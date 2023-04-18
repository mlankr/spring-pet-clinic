package com.turing.springpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Milan on 2023/04/18.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Speciality extends BaseEntity {

    private String description;
}
