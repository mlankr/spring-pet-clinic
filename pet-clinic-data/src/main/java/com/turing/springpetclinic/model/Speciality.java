package com.turing.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Milan on 2023/04/18.
 */

@Getter
public class Speciality extends BaseEntity {

    private final String description;

    @Builder(toBuilder = true)
    public Speciality(@Builder.ObtainVia(method = "getId") Long id, String description) {
        super(id);
        this.description = description;
    }
}
