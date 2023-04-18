package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Milan on 2023/02/14.
 */

@Builder
@Getter
public class PetType extends BaseEntity {

    private String name;
}
