package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Milan on 2023/02/18.
 */
@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Pet pet) {
		super.delete(pet);
	}

	@Override
	public Pet save(Pet pet) {
		return super.save(pet);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
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
