package com.turing.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.repositories.OwnerRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

	private final Long ownerId = 1L;
	private final String lastname = "Muster";
	@Mock
	private OwnerRepository ownerRepository;
	@InjectMocks
	private OwnerJpaService service;
	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = Owner.builder()
			.lastname(lastname)
			.build();
	}

	@Test
	void findAll() {
		when(ownerRepository.findAll()).thenReturn(Set.of(owner));

		assertEquals(1, service.findAll()
			.size());
		verify(ownerRepository, times(1)).findAll();
	}

	@Test
	void findById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

		assertNotNull(service.findById(ownerId));
		verify(ownerRepository, times(1)).findById(ownerId);
	}

	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertNull(service.findById(ownerId));
		verify(ownerRepository, times(1)).findById(ownerId);
	}

	@Test
	void save() {
		Owner owner2 = Owner.builder()
			.build();

		when(ownerRepository.save(any())).thenReturn(owner2);

		Owner savedOwner = service.save(owner2);

		assertNotNull(savedOwner);
		verify(ownerRepository, times(1)).save(owner2);
	}

	@Test
	void delete() {
		service.delete(owner);

		assertEquals(0, service.findAll()
			.size());
		verify(ownerRepository, times(1)).delete(owner);
	}

	@Test
	void deleteById() {
		service.deleteById(ownerId);

		assertEquals(0, service.findAll()
			.size());
		verify(ownerRepository, times(1)).deleteById(ownerId);
	}

	@Test
	void findByLastName() {
		when(ownerRepository.findByLastname(any())).thenReturn(owner);

		Owner foundOwner = service.findByLastName(lastname);

		assertEquals(owner, foundOwner);
		verify(ownerRepository, times(1)).findByLastname(lastname);
	}
}
