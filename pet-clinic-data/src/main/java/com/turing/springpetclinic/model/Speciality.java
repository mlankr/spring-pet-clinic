package com.turing.springpetclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Milan on 2023/04/18.
 */

@Getter
@NoArgsConstructor
@Entity
@Table
public class Speciality extends BaseEntity {

    private String description;

    @Builder(toBuilder = true)
    public Speciality(@Builder.ObtainVia(method = "getId") Long id, String description) {
        super(id);
        this.description = description;
    }
}
