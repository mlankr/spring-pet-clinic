package com.turing.springpetclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.VetService;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

	@Mock
	private VetService vetService;

	@InjectMocks
	private VetController controller;

	private Set<Vet> vets;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		vets = Set.of(Vet.builder()
			.build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.build();
	}

	@Test
	void listVets() throws Exception {
		when(vetService.findAll()).thenReturn(vets);

		mockMvc.perform(MockMvcRequestBuilders.get("/vets"))
			.andExpect(MockMvcResultMatchers.status()
				.isOk())
			.andExpect(MockMvcResultMatchers.view()
				.name("vets/index"))
			.andExpect(MockMvcResultMatchers.model()
				.attribute("vets", hasSize(1)));

		verify(vetService, times(1)).findAll();
	}

	@Test
	void listVetsByIndex() throws Exception {
		when(vetService.findAll()).thenReturn(vets);

		mockMvc.perform(MockMvcRequestBuilders.get("/vets/index"))
			.andExpect(MockMvcResultMatchers.status()
				.isOk())
			.andExpect(MockMvcResultMatchers.view()
				.name("vets/index"))
			.andExpect(MockMvcResultMatchers.model()
				.attribute("vets", hasSize(1)));

		verify(vetService, times(1)).findAll();
	}
}
