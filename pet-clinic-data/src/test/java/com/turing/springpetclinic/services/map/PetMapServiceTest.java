package com.turing.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.turing.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetMapServiceTest {

	private final Long petId = 1L;
	private PetMapService petMapService;

	@BeforeEach
	void setUp() {
		petMapService = new PetMapService();
		petMapService.save(Pet.builder()
			.build());
	}

	@Test
	void findAll() {
		assertEquals(1, petMapService.findAll()
			.size());
	}

	@Test
	void delete() {
		petMapService.delete(petMapService.findById(petId));
		assertEquals(0, petMapService.findAll()
			.size());
	}

	@Test
	void save() {
		Long secondId = 2L;

		Pet savedPet = petMapService.save(Pet.builder()
			.build());
		assertEquals(secondId, savedPet.getId());
	}

	@Test
	void deleteById() {
		petMapService.deleteById(petId);
		assertEquals(0, petMapService.findAll()
			.size());
	}

	@Test
	void findById() {
		assertEquals(petId, petMapService.findById(petId)
			.getId());
	}
}
