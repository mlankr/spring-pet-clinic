package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Milan on 2023/02/14.
 */


@Getter
public class PetType extends BaseEntity {

    private final String name;

    @Builder
    public PetType(@Builder.ObtainVia(method = "getId") Long id, String name) {
        super(id);
        this.name = name;
    }
}
