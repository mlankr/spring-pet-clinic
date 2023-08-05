package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.PetTypeService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/02/18.
 */
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerMapService(PetTypeService petTypeService, PetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Owner owner) {
		super.delete(owner);
	}

	@Override
	public Owner save(Owner owner) {
		if (owner != null) {
			if (owner.getPets() != null) {
				owner.getPets()
						.forEach(pet -> {
							if (pet.getPetType() != null) {
								if (pet.getPetType()
										.getId() == null) {
									pet.toBuilder()
											.petType(petTypeService.save(pet.getPetType()));
								}
							} else {
								throw new RuntimeException("Pet Type is required!");
							}

							if (pet.getId() == null) {
								Pet savedPet = petService.save(pet);
								pet.toBuilder()
										.id(savedPet.getId());
							}
						});

			}
			return super.save(owner);
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner update(Owner owner, Long id) {
		Owner currentOwner = this.findById(id);
		if (currentOwner != null) {
			owner.setId(id);
			return this.save(owner);
		}
		throw new RuntimeException("Owner doesn't exist!");
	}

	@Override
	public Owner findByLastName(String lastName) {
		return this.findAll()
				.stream()
				.filter(o -> o.getLastname()
						.equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return this.findAll()
				.stream()
				.filter(o -> o.getLastname()
						.contains(lastName))
				.collect(Collectors.toList());
	}
}
