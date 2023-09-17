package com.turing.springpetclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
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
import com.turing.springpetclinic.services.VisitService;
import com.turing.springpetclinic.utils.Helper;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

/**
 * Created by Milan on 2023/07/15.
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

	private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
	private final Map<String, String> uriVariables = new HashMap<>();
	@Mock
	private OwnerService ownerService;
	@Mock
	private VisitService visitService;
	@Mock
	private PetService petService;
	@InjectMocks
	private VisitController visitController;
	private Owner owner;
	private Pet pet;
	private MockMvc mockMvc;
	private URI visitsUri;

	@BeforeEach
	void setUp() {
		long petId = 1L;
		long ownerId = 1L;

		pet = Pet.builder()
			.id(petId)
			.birthDate(Helper.localDateToDate(LocalDate.of(2018, 11, 13)))
			.name("Cutie")
			.visits(new HashSet<>())
			.owner(owner)
			.petType(PetType.builder()
				.name("Dog")
				.build())
			.build();

		owner = Owner.builder()
			.firstname("Joe")
			.lastname("Doe")
			.pet(pet)
			.build();

		when(petService.findById(anyLong())).thenReturn(pet);

		uriVariables.clear();
		uriVariables.put("ownerId", Long.toString(ownerId));
		uriVariables.put("petId", String.valueOf(petId));
		visitsUri = visitsUriTemplate.expand(uriVariables);

		mockMvc = MockMvcBuilders.standaloneSetup(visitController)
			.build();
	}

	@Test
	void initNewVisitForm() throws Exception {
		mockMvc.perform(get(visitsUri))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdateVisitForm"));
	}


	@Test
	void processNewVisitForm() throws Exception {
		mockMvc.perform(post(visitsUri).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("date", "2018-11-11")
				.param("description", "yet another visit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}
}
