package com.turing.springpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class IndexControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		IndexController controller = new IndexController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.build();
	}

	@Test
	void index() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status()
				.isOk())
			.andExpect(MockMvcResultMatchers.view()
				.name("index"));
	}

	@Test
	void error() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/oops"))
			.andExpect(MockMvcResultMatchers.status()
				.isOk())
			.andExpect(MockMvcResultMatchers.view()
				.name("notImplemented"));
	}
}
