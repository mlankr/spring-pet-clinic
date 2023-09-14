package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.VetService;
import com.turing.springpetclinic.services.VisitService;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Milan on 2023/09/14.
 */

@RestController
@RequestMapping("/api/")
public class EntitiesRestController {

	private final OwnerService ownerService;
	private final PetService petService;
	private final VetService vetService;
	private final VisitService visitService;

	public EntitiesRestController(OwnerService ownerService, PetService petService, VetService vetService, VisitService visitService) {
		this.ownerService = ownerService;
		this.petService = petService;
		this.vetService = vetService;
		this.visitService = visitService;
	}

	@GetMapping({"owners"})
	public @ResponseBody Set<Owner> ownersJson() {
		return ownerService.findAll();
	}

	@GetMapping({"pets"})
	public @ResponseBody Set<Pet> petsJson() {
		return petService.findAll();
	}

	@GetMapping({"vets"})
	public @ResponseBody Set<Vet> vetsJson() {
		return vetService.findAll();
	}

	@GetMapping({"visits"})
	public @ResponseBody Set<Visit> visitsJson() {
		return visitService.findAll();
	}
}
