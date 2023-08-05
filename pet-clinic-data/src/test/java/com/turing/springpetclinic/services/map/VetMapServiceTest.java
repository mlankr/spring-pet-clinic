package com.turing.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VetMapServiceTest {

	private final Long vetId = 1L;
	@Mock
	private SpecialityService specialityService;
	@InjectMocks
	private VetMapService vetMapService;

	@BeforeEach
	void setUp() {
		vetMapService.save(Vet.builder()
				.build());
	}

	@Test
	void findAll() {
		assertEquals(1, vetMapService.findAll()
				.size());
	}

	@Test
	void delete() {
		vetMapService.delete(vetMapService.findById(vetId));
		assertEquals(0, vetMapService.findAll()
				.size());
	}

	@Test
	void save() {
		Long secondId = 2L;
		Vet savedVet = vetMapService.save(Vet.builder()
				.build());
		assertEquals(secondId, savedVet.getId());
	}

	@Test
	void deleteById() {
		vetMapService.deleteById(vetId);
		assertEquals(0, vetMapService.findAll()
				.size());
	}

	@Test
	void findById() {
		assertEquals(vetId, vetMapService.findById(vetId)
				.getId());
	}
}