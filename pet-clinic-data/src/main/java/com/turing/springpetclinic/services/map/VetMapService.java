package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.SpecialityService;
import com.turing.springpetclinic.services.VetService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/02/18.
 */
@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialityService specialityService;

	public VetMapService(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Vet vet) {
		super.delete(vet);
	}

	@Override
	public Vet save(Vet vet) {

		if (vet.getSpecialties()
			.size() > 0) {
			vet.getSpecialties()
				.forEach(speciality -> {
					if (speciality.getId() == null) {
						Speciality savedSpecialty = specialityService.save(speciality);
						speciality.toBuilder()
							.id(savedSpecialty.getId())
							.build();
					}
				});
		}
		return super.save(vet);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
}
