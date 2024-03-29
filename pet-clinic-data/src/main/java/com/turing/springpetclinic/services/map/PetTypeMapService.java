package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.services.PetTypeService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Milan on 2023/04/18.
 */

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

	@Override
	public Set<PetType> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(PetType object) {
		super.delete(object);
	}

	@Override
	public PetType save(PetType object) {
		return super.save(object);
	}

	@Override
	public PetType findById(Long id) {
		return super.findById(id);
	}
}
