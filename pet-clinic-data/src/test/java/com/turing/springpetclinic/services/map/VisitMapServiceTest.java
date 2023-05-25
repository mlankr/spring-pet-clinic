package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitMapServiceTest {

    private VisitMapService visitMapService;

    private final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().build();
        PetType petType = PetType.builder().build();
        Pet pet = Pet.builder().owner(owner).petType(petType).build();

        visitMapService = new VisitMapService();
        visitMapService.save(Visit.builder().pet(pet).build());
    }

    @Test
    void findAll() {
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(visitId, visitMapService.findById(visitId).getId());
    }

    @Test
    void save() {
        Long secondId = 2L;

        Owner owner2 = Owner.builder().build();
        PetType petType2 = PetType.builder().build();
        Pet pet2 = Pet.builder().owner(owner2).petType(petType2).build();
        Visit savedVisit = visitMapService.save(Visit.builder().pet(pet2).build());
        assertEquals(secondId, savedVisit.getId());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(visitId));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(visitId);
        assertEquals(0, visitMapService.findAll().size());
    }
}