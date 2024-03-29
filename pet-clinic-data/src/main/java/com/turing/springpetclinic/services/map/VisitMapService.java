package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.services.VisitService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/05/09.
 */
@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

	@Override
	public Set<Visit> findAll() {
		return super.findAll();
	}

	@Override
	public Visit findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Visit save(Visit visit) {
		if (visit.getPet() == null || visit.getPet()
			.getOwner() == null || visit.getPet()
			.getPetType() == null) {
			throw new RuntimeException("Invalid Visit");
		}
		return super.save(visit);
	}

	@Override
	public void delete(Visit visit) {
		super.delete(visit);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
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
