package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.repositories.OwnerRepository;
import com.turing.springpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Milan on 2023/05/08.
 */

@Service
@Profile("springdatajpa")
public class OwnerJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerJpaService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
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
	public void delete(Owner owner) {
		ownerRepository.delete(owner);
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastname(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findByLastnameLike(lastName);
	}
}