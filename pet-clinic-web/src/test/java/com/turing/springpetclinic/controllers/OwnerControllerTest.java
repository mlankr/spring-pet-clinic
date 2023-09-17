package com.turing.springpetclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.services.OwnerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
		owners = Set.of(Owner.builder()
			.firstname("Max")
			.lastname("Muster")
			.build());

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.build();
	}

	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owners.stream()
			.findFirst()
			.orElse(null));

		mockMvc.perform(get("/owners/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attribute("owner", notNullValue()))
			.andExpect(model().attribute("owner", hasProperty("firstname", is("Max"))))
			.andExpect(model().attribute("owner", hasProperty("lastname", is("Muster"))));

		verify(ownerService, times(1)).findById(anyLong());
	}

	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"))
			.andExpect(model().attributeExists("owner"));

		verifyNoInteractions(ownerService);
	}

	@Test
	void processFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder()
			.build(), Owner.builder()
			.build()));

		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/allOwners"))
			.andExpect(model().attribute("selections", hasSize(2)));
	}

	@Test
	void processFindFormEmptyReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder()
			.build(), Owner.builder()
			.build()));

		mockMvc.perform(get("/owners").param("lastName", ""))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/allOwners"))
			.andExpect(model().attribute("selections", hasSize(2)));
		;
	}

	@Test
	void processFindFormReturnOne() throws Exception {
		Owner owner = Owner.builder()
			.build();

		when(ownerService.findAllByLastNameLike(Mockito.any(String.class))).thenAnswer(invocation -> {
			owner.setId(1L); // Set the desired ID
			return Collections.singletonList(owner);
		});


		mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/allOwners"));
	}

	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));

		verifyNoInteractions(ownerService);
	}

	@Test
	void processCreationForm() throws Exception {
		Owner owner = Owner.builder()
			.build();

		when(ownerService.save(ArgumentMatchers.any())).thenAnswer(invocation -> {
			owner.setId(1L); // Set the desired ID
			return owner;
		});

		mockMvc.perform(post("/owners/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));

		verify(ownerService).save(ArgumentMatchers.any());
	}

	@Test
	void initUpdateOwnerForm() throws Exception {
		Owner owner = Owner.builder()
			.build();

		when(ownerService.findById(anyLong())).thenAnswer(invocation -> {
			owner.setId(1L); // Set the desired ID
			return owner;
		});

		mockMvc.perform(get("/owners/1/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(model().attributeExists("owner"));

		verify(ownerService, times(1)).findById(anyLong());
	}

	@Test
	void processUpdateOwnerForm() throws Exception {
		Owner owner = Owner.builder()
			.build();

		when(ownerService.update(ArgumentMatchers.any(), anyLong())).thenAnswer(invocation -> {
			owner.setId(1L); // Set the desired ID
			return owner;
		});

		mockMvc.perform(post("/owners/1/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));

		verify(ownerService).update(ArgumentMatchers.any(), anyLong());
	}
}
