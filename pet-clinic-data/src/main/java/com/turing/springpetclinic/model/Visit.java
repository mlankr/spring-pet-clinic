package com.turing.springpetclinic.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Milan on 2023/04/18.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table
public class Visit extends BaseEntity {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@Column(length = 1024)
	private String description;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
}