package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.VetService;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Milan on 2023/02/19.
 */
@Controller
public class VetController {

	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping({"/vets", "/vets/", "/vets/index", "/vets/index.html"})
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());
		return "vets/index";
	}

	@GetMapping({"/api/vets"})
	public @ResponseBody Set<Vet> vetsJson() {
		return vetService.findAll();
	}

	@GetMapping("/vets/*")
	public String notImplemented() {
		return "oops/notFound";
	}
}
