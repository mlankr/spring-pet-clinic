package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.BaseEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Milan on 2023/02/18.
 */

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

	protected Map<Long, T> map = new HashMap<>();

	Set<T> findAll() {
		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	T save(T object) {
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextId());
			}

			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("Object cannot be null");
		}
		return object;
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	void delete(T object) {
		map.entrySet()
			.removeIf(obj -> obj.getValue()
				.equals(object));
	}

	private Long getNextId() {
		return map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1;
	}
}
