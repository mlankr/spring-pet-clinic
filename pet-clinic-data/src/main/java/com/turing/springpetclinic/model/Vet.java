package com.turing.springpetclinic.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@NoArgsConstructor
@Entity
@Table
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialties;

    @Builder(toBuilder = true)
    public Vet(@Builder.ObtainVia(method = "getFirstname") String firstname,
               @Builder.ObtainVia(method = "getLastname") String lastname,
               @Singular Set<Speciality> specialties) {
        super(firstname, lastname);
        this.specialties = specialties;
    }
}
