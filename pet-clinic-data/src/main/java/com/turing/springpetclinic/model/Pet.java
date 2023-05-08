package com.turing.springpetclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@NoArgsConstructor
@Entity
@Table
public class Pet extends BaseEntity {

    private String name;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Builder(toBuilder = true)
    public Pet(@Builder.ObtainVia(method = "getId") Long id, String name, PetType petType, Owner owner,
               LocalDate birthDate) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
    }
}
