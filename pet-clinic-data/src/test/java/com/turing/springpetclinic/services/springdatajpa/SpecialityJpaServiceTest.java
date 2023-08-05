package com.turing.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.repositories.SpecialityRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialityJpaServiceTest {

	private final Long specialityId = 1L;
	@Mock
	private SpecialityRepository specialityRepository;
	@InjectMocks
	private SpecialityJpaService service;
	private Speciality speciality;

	@BeforeEach
	void setUp() {
		speciality = Speciality.builder()
			.build();
	}

	@Test
	void findAll() {
		when(specialityRepository.findAll()).thenReturn(Set.of(speciality));

		assertEquals(1, service.findAll()
			.size());
		verify(specialityRepository, times(1)).findAll();
	}

	@Test
	void findById() {
		when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(speciality));

		assertNotNull(service.findById(specialityId));
		verify(specialityRepository, times(1)).findById(specialityId);
	}

	@Test
	void save() {
		Speciality speciality2 = Speciality.builder()
			.build();

		when(specialityRepository.save(any())).thenReturn(speciality2);

		Speciality savedSpeciality = service.save(speciality2);

		assertNotNull(savedSpeciality);
		verify(specialityRepository, times(1)).save(speciality2);
	}

	@Test
	void delete() {
		service.delete(speciality);

		assertEquals(0, service.findAll()
			.size());
		verify(specialityRepository, times(1)).delete(speciality);
	}

	@Test
	void deleteById() {
		service.deleteById(specialityId);

		assertEquals(0, service.findAll()
			.size());
		verify(specialityRepository, times(1)).deleteById(specialityId);
	}
}
