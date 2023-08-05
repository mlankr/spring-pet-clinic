package com.turing.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.repositories.PetRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetJpaServiceTest {

	private final Long petId = 1L;
	@Mock
	private PetRepository petRepository;
	@InjectMocks
	private PetJpaService service;
	private Pet pet;

	@BeforeEach
	void setUp() {
		pet = Pet.builder()
				.build();
	}

	@Test
	void findAll() {
		when(petRepository.findAll()).thenReturn(Set.of(pet));

		assertEquals(1, service.findAll()
				.size());
		verify(petRepository, times(1)).findAll();
	}

	@Test
	void findById() {
		when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet));

		assertNotNull(service.findById(petId));
		verify(petRepository, times(1)).findById(petId);
	}

	@Test
	void save() {
		Pet pet2 = Pet.builder()
				.build();

		when(petRepository.save(any())).thenReturn(pet2);

		Pet savedPet = service.save(pet2);

		assertNotNull(savedPet);
		verify(petRepository, times(1)).save(pet2);
	}

	@Test
	void delete() {
		service.delete(pet);

		assertEquals(0, service.findAll()
				.size());
		verify(petRepository, times(1)).delete(pet);
	}

	@Test
	void deleteById() {
		service.deleteById(petId);

		assertEquals(0, service.findAll()
				.size());
		verify(petRepository, times(1)).deleteById(petId);
	}
}