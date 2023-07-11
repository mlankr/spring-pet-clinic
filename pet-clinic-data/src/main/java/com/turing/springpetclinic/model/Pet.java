package com.turing.springpetclinic.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Pet extends BaseEntity {

	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType petType;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits;

	@Builder(toBuilder = true)
	public Pet(@Builder.ObtainVia(method = "getId") Long id, String name, Date birthDate, PetType petType, Owner owner,
			@Singular Set<Visit> visits) {
		super(id);
		this.name = name;
		this.birthDate = birthDate;
		this.petType = petType;
		this.owner = owner;
		this.visits = visits;
	}
}
