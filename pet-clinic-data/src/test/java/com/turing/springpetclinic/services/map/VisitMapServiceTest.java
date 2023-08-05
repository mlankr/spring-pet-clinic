package com.turing.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitMapServiceTest {

	private final Long visitId = 1L;
	@Mock
	private Owner owner;
	@Mock
	private Pet pet;
	@Mock
	private PetType petType;
	@InjectMocks
	private VisitMapService visitMapService;

	@BeforeEach
	void setUp() {
		when(pet.getOwner()).thenReturn(owner);
		when(pet.getPetType()).thenReturn(petType);

		visitMapService.save(Visit.builder()
				.pet(pet)
				.build());
	}

	@Test
	void findAll() {
		assertEquals(1, visitMapService.findAll()
				.size());
	}

	@Test
	void findById() {
		assertEquals(visitId, visitMapService.findById(visitId)
				.getId());
	}

	@Test
	void save() {
		Long secondId = 2L;

		Owner owner2 = mock(Owner.class);
		PetType petType2 = mock(PetType.class);
		Pet pet2 = mock(Pet.class);

		when(pet2.getOwner()).thenReturn(owner2);
		when(pet2.getPetType()).thenReturn(petType2);

		Visit savedVisit = visitMapService.save(Visit.builder()
				.pet(pet2)
				.build());
		assertEquals(secondId, savedVisit.getId());
	}

	@Test
	void delete() {
		visitMapService.delete(visitMapService.findById(visitId));
		assertEquals(0, visitMapService.findAll()
				.size());
	}

	@Test
	void deleteById() {
		visitMapService.deleteById(visitId);
		assertEquals(0, visitMapService.findAll()
				.size());
	}
}