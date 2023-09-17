package com.turing.springpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Milan on 2023/04/18.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
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
