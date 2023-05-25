package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetTypeJpaServiceTest {

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private PetTypeJpaService service;

    private PetType petType;
    private final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petType = PetType.builder().build();
    }

    @Test
    void findAll() {
        when(petTypeRepository.findAll()).thenReturn(Set.of(petType));

        assertEquals(1, service.findAll().size());
        verify(petTypeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(petType));

        assertNotNull(service.findById(petTypeId));
        verify(petTypeRepository, times(1)).findById(petTypeId);
    }

    @Test
    void save() {
        PetType petType2 = PetType.builder().build();

        when(petTypeRepository.save(any())).thenReturn(petType2);

        PetType savedPetType = service.save(petType2);

        assertNotNull(savedPetType);
        verify(petTypeRepository, times(1)).save(petType2);
    }

    @Test
    void delete() {
        service.delete(petType);

        assertEquals(0, service.findAll().size());
        verify(petTypeRepository, times(1)).delete(petType);
    }

    @Test
    void deleteById() {
        service.deleteById(petTypeId);

        assertEquals(0, service.findAll().size());
        verify(petTypeRepository, times(1)).deleteById(petTypeId);
    }
}