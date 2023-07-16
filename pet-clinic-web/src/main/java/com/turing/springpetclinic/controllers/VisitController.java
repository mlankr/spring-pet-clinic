package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Milan on 2023/07/15.
 */
@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

	private final VisitService visitService;
	private final OwnerService ownerService;
	private final PetService petService;

	public VisitController(PetService petService, VisitService visitService, OwnerService ownerService) {
		this.petService = petService;
		this.visitService = visitService;
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId,
			Model model) {

		Pet pet = petService.findById(petId);
		Visit visit = Visit.builder().pet(pet).build();
		pet = pet.toBuilder().visit(visit).build();

		model.addAttribute("pet", pet);

		Owner owner = ownerService.findById(petId);
		model.addAttribute("owner", owner);

		return visit;
	}

	@GetMapping("/new")
	public String initNewVisitForm(@PathVariable Long ownerId, Model model, @PathVariable Long petId) {
		model.addAttribute("pet", petService.findById(petId));
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/new")
	public String processNewVisitForm(@PathVariable Long petId, @Validated Visit visit, Owner owner,
			BindingResult result, @PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		visitService.save(visit);
		return "redirect:/owners/{ownerId}";
	}
}
