package com.turing.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.turing.springpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetTypeMapServiceTest {

	private final Long petTypeId = 1L;
	private PetTypeMapService petTypeMapService;

	@BeforeEach
	void setUp() {
		petTypeMapService = new PetTypeMapService();
		petTypeMapService.save(PetType.builder()
			.build());
	}

	@Test
	void findAll() {
		assertEquals(1, petTypeMapService.findAll()
			.size());
	}

	@Test
	void deleteById() {
		petTypeMapService.deleteById(petTypeId);
		assertEquals(0, petTypeMapService.findAll()
			.size());
	}

	@Test
	void delete() {
		petTypeMapService.delete(petTypeMapService.findById(petTypeId));
		assertEquals(0, petTypeMapService.findAll()
			.size());
	}

	@Test
	void save() {
		Long secondId = 2L;

		PetType savedPetType = petTypeMapService.save(PetType.builder()
			.build());
		assertEquals(secondId, savedPetType.getId());
	}

	@Test
	void findById() {
		assertEquals(petTypeId, petTypeMapService.findById(petTypeId)
			.getId());
	}
}
