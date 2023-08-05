package com.turing.springpetclinic.services.springdatajpa;

import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.repositories.SpecialityRepository;
import com.turing.springpetclinic.services.SpecialityService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/05/09.
 */

@Service
@Profile("springdatajpa")
public class SpecialityJpaService implements SpecialityService {

	private final SpecialityRepository specialityRepository;

	public SpecialityJpaService(SpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialities = new HashSet<>();
		specialityRepository.findAll()
			.forEach(specialities::add);
		return specialities;
	}

	@Override
	public Speciality findById(Long id) {
		return specialityRepository.findById(id)
			.orElse(null);
	}

	@Override
	public Speciality save(Speciality speciality) {
		return specialityRepository.save(speciality);
	}

	@Override
	public void delete(Speciality speciality) {
		specialityRepository.delete(speciality);
	}

	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById(id);
	}
}
