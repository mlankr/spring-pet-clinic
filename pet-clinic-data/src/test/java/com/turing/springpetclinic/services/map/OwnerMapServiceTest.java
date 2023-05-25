package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private PetService petService;

    private OwnerMapService ownerMapService;

    private final long ownerId = 1L;
    private final String lastName = "Muster";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(petTypeService, petService);
        ownerMapService.save(Owner.builder().lastname(lastName).build());
    }

    @Test
    void findAll() {
        assertEquals(ownerMapService.findAll().size(), 1);
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        Long secondOwnerId = 2L;
        Owner owner2 = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(secondOwnerId, savedOwner.getId());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(ownerId, ownerMapService.findById(ownerId).getId());
    }

    @Test
    void findByLastName() {
        assertEquals(lastName, ownerMapService.findByLastName(lastName).getLastname());
    }

    @Test
    void findByLastNameNotFound() {
        assertNull(ownerMapService.findByLastName("foo"));
    }
}