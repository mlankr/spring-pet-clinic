package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.services.SpecialityService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/04/18.
 */
@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {

	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Speciality speciality) {
		super.delete(speciality);
	}

	@Override
	public Speciality save(Speciality speciality) {
		return super.save(speciality);
	}

	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}
}
