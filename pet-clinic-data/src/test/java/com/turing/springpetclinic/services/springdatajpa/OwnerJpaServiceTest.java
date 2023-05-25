package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService service;

    private Owner owner;

    private final Long ownerId = 1L;
    private final String lastname = "Muster";

    @BeforeEach
    void setUp() {
        owner = Owner.builder().lastname(lastname).build();
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(Set.of(owner));

        assertEquals(1, service.findAll().size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        assertNotNull(service.findById(ownerId));
        verify(ownerRepository, times(1)).findById(ownerId);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertNull(service.findById(ownerId));
        verify(ownerRepository, times(1)).findById(ownerId);
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().build();

        when(ownerRepository.save(any())).thenReturn(owner2);

        Owner savedOwner = service.save(owner2);

        assertNotNull(savedOwner);
        verify(ownerRepository, times(1)).save(owner2);
    }

    @Test
    void delete() {
        service.delete(owner);

        assertEquals(0, service.findAll().size());
        verify(ownerRepository, times(1)).delete(owner);
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);

        assertEquals(0, service.findAll().size());
        verify(ownerRepository, times(1)).deleteById(ownerId);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastname(any())).thenReturn(owner);

        Owner foundOwner = service.findByLastName(lastname);

        assertEquals(owner, foundOwner);
        verify(ownerRepository, times(1)).findByLastname(lastname);
    }
}