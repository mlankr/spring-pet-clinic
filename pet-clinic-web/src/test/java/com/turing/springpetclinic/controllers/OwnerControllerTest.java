package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	private OwnerService ownerService;

	@InjectMocks
	private OwnerController controller;

	private Set<Owner> owners;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		owners = Set.of(Owner.builder().firstname("Max").lastname("Muster").build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void listOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);

		mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/index"))
			   .andExpect(model().attribute("owners", hasSize(1)));

		verify(ownerService, times(1)).findAll();
	}

	@Test
	void listOwnersByIndex() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);

		mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/index"))
			   .andExpect(model().attribute("owners", hasSize(1)));

		verify(ownerService, times(1)).findAll();
	}

	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owners.stream().findFirst().orElse(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("owners/ownerDetails"))
			   .andExpect(model().attribute("owner", notNullValue()))
			   .andExpect(model().attribute("owner", hasProperty("firstname", is("Max"))))
			   .andExpect(model().attribute("owner", hasProperty("lastname", is("Muster"))));

		verify(ownerService, times(1)).findById(anyLong());
	}
}