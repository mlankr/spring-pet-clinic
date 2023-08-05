package com.turing.springpetclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.PetTypeService;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

	@Mock
	private PetService petService;

	@Mock
	private OwnerService ownerService;

	@Mock
	private PetTypeService petTypeService;

	@InjectMocks
	private PetController petController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		Owner owner = Owner.builder()
			.build();

		Set<PetType> petTypes =
			Set.of(PetType.builder()
				.id(1L)
				.name("Dog")
				.build(), PetType.builder()
				.id(2L)
				.name("Cat")
				.build());

		mockMvc = MockMvcBuilders.standaloneSetup(petController)
			.build();

		when(ownerService.findById(ArgumentMatchers.any())).thenAnswer(invocation -> {
			owner.setId(1L); // Set the desired ID
			return owner;
		});

		when(petTypeService.findAll()).thenReturn(petTypes);
	}

	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/1/pets/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void processCreationForm() throws Exception {
		when(petService.save(any())).thenReturn(Pet.builder()
			.id(1L)
			.build());
		mockMvc.perform(post("/owners/1/pets/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));

		verify(petService, times(1)).save(any());
	}

	@Test
	void initUpdateForm() throws Exception {
		when(petService.findById(anyLong())).thenReturn(Pet.builder()
			.id(2L)
			.name("Katy")
			.build());

		mockMvc.perform(get("/owners/1/pets/2/edit"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void processUpdateForm() throws Exception {
		when(petService.update(any(), anyLong())).thenReturn(Pet.builder()
			.id(1L)
			.build());

		mockMvc.perform(post("/owners/1/pets/2/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));

		verify(petService, times(1)).update(any(), anyLong());
	}
}
