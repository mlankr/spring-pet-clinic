package com.turing.springpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;
}