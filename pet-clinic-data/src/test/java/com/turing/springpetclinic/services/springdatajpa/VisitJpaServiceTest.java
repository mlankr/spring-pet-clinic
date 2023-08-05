package com.turing.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.repositories.VisitRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitJpaServiceTest {

	private final Long visitId = 1L;
	@Mock
	private VisitRepository visitRepository;
	@InjectMocks
	private VisitJpaService service;
	private Visit visit;

	@BeforeEach
	void setUp() {
		visit = Visit.builder()
			.build();
	}

	@Test
	void findAll() {
		when(visitRepository.findAll()).thenReturn(Set.of(visit));

		assertEquals(1, service.findAll()
			.size());
		verify(visitRepository, times(1)).findAll();
	}

	@Test
	void findById() {
		when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

		assertNotNull(service.findById(visitId));
		verify(visitRepository, times(1)).findById(visitId);
	}

	@Test
	void save() {
		Visit petType2 = Visit.builder()
			.build();

		when(visitRepository.save(any())).thenReturn(petType2);

		Visit savedVisit = service.save(petType2);

		assertNotNull(savedVisit);
		verify(visitRepository, times(1)).save(petType2);
	}

	@Test
	void delete() {
		service.delete(visit);

		assertEquals(0, service.findAll()
			.size());
		verify(visitRepository, times(1)).delete(visit);
	}

	@Test
	void deleteById() {
		service.deleteById(visitId);

		assertEquals(0, service.findAll()
			.size());
		verify(visitRepository, times(1)).deleteById(visitId);
	}
}
