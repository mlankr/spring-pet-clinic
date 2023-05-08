package com.turing.springpetclinic.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits;

    @Builder(toBuilder = true)
    public Pet(@Builder.ObtainVia(method = "getId") Long id, String name, LocalDate birthDate,
               PetType petType, Owner owner, @Singular Set<Visit> visits) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        this.visits = visits;
    }
}
