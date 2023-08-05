package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.repositories.PetRepository;
import com.turing.springpetclinic.services.PetService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/05/09.
 */

@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService {

	private final PetRepository petRepository;

	public PetJpaService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		petRepository.findAll()
			.forEach(pets::add);
		return pets;
	}

	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id)
			.orElse(null);
	}

	@Override
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public void delete(Pet pet) {
		petRepository.delete(pet);
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public Pet update(Pet pet, Long id) {
		Pet currentPet = this.findById(id);
		if (currentPet != null) {
			pet.setId(id);
			return this.save(pet);
		}
		throw new RuntimeException("Pet doesn't exist!");
	}
}
