package com.turing.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.repositories.VetRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VetJpaServiceTest {

	private final Long vetId = 1L;
	@Mock
	private VetRepository vetRepository;
	@InjectMocks
	private VetJpaService service;
	private Vet vet;

	@BeforeEach
	void setUp() {
		vet = Vet.builder()
			.build();
	}

	@Test
	void findAll() {
		when(vetRepository.findAll()).thenReturn(Set.of(vet));

		assertEquals(1, service.findAll()
			.size());
		verify(vetRepository, times(1)).findAll();
	}

	@Test
	void findById() {
		when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));

		assertNotNull(service.findById(vetId));
		verify(vetRepository, times(1)).findById(vetId);
	}

	@Test
	void save() {
		Vet vet2 = Vet.builder()
			.build();

		when(vetRepository.save(any())).thenReturn(vet2);

		Vet savedVet = service.save(vet2);

		assertNotNull(savedVet);
		verify(vetRepository, times(1)).save(vet2);
	}

	@Test
	void delete() {
		service.delete(vet);

		assertEquals(0, service.findAll()
			.size());
		verify(vetRepository, times(1)).delete(vet);
	}

	@Test
	void deleteById() {
		service.deleteById(vetId);

		assertEquals(0, service.findAll()
			.size());
		verify(vetRepository, times(1)).deleteById(vetId);
	}
}
