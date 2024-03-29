package com.turing.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.turing.springpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialityMapServiceTest {

	private final Long specialityId = 1L;
	private SpecialityMapService specialityMapService;

	@BeforeEach
	void setUp() {
		specialityMapService = new SpecialityMapService();
		specialityMapService.save(Speciality.builder()
			.build());
	}

	@Test
	void findAll() {
		assertEquals(1, specialityMapService.findAll()
			.size());
	}

	@Test
	void deleteById() {
		specialityMapService.deleteById(specialityId);
		assertEquals(0, specialityMapService.findAll()
			.size());
	}

	@Test
	void delete() {
		specialityMapService.delete(specialityMapService.findById(specialityId));
		assertEquals(0, specialityMapService.findAll()
			.size());
	}

	@Test
	void save() {
		Long secondId = 2L;
		Speciality savedSpecialty = specialityMapService.save(Speciality.builder()
			.build());
		assertEquals(secondId, savedSpecialty.getId());
	}

	@Test
	void findById() {
		assertEquals(specialityId, specialityMapService.findById(specialityId)
			.getId());
	}
}
