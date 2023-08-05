package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.repositories.VisitRepository;
import com.turing.springpetclinic.services.VisitService;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/05/09.
 */

@Service
@Profile("springdatajpa")
public class VisitJpaService implements VisitService {

	private final VisitRepository visitRepository;

	public VisitJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		visitRepository.findAll()
				.forEach(visits::add);
		return visits;
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id)
				.orElse(null);
	}

	@Override
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public void delete(Visit visit) {
		visitRepository.delete(visit);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

	@Override
	public Set<Visit> findVisitsByPet(long petId) {
		return findAll().stream()
				.filter(v -> v.getPet()
						.getId()
						.equals(petId))
				.collect(Collectors.toSet());
	}
}
