package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.repositories.VetRepository;
import com.turing.springpetclinic.services.VetService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/05/09.
 */

@Service
@Profile("springdatajpa")
public class VetJpaService implements VetService {

	private final VetRepository vetRepository;

	public VetJpaService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		vetRepository.findAll()
			.forEach(vets::add);
		return vets;
	}

	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id)
			.orElse(null);
	}

	@Override
	public Vet save(Vet vet) {
		return vetRepository.save(vet);
	}

	@Override
	public void delete(Vet vet) {
		vetRepository.delete(vet);
	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}
}
