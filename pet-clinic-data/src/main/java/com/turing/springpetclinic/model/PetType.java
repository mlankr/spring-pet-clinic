package com.turing.springpetclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

	private String name;

	@Builder
	public PetType(@Builder.ObtainVia(method = "getId") Long id, String name) {
		super(id);
		this.name = name;
	}
}
