package com.turing.springpetclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Created by Milan on 2023/02/14.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
@JsonIgnoreProperties({"pets"})
public class Owner extends Person {

	private String address;
	private String city;
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets;

	@Builder(toBuilder = true)
	public Owner(@Builder.ObtainVia(method = "getFirstname") String firstname,
		@Builder.ObtainVia(method = "getLastname") String lastname, String address, String city, String telephone,
		@Singular Set<Pet> pets) {
		super(firstname, lastname);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		this.pets = pets;
	}
}
