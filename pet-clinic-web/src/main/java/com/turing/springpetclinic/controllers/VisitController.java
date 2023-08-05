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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		Visit visit = Visit.builder()
			.pet(pet)
			.build();
		pet = pet.toBuilder()
			.visit(visit)
			.build();

		Owner owner = ownerService.findById(ownerId);
		model.addAttribute("owner", owner);

		model.addAttribute("pet", pet);

		return visit;
	}

	@GetMapping("/new")
	public String initNewVisitForm(@PathVariable Long ownerId, Model model, @PathVariable Long petId) {
		model.addAttribute("pet", petService.findById(petId));
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/new")
	public String processNewVisitForm(@PathVariable Long petId, @Validated Visit visit, BindingResult result,
		@PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		visitService.save(visit);
		return "redirect:/owners/{ownerId}";
	}

	@RequestMapping("/{visitId}/delete")
	public String deleteVisit(@PathVariable Long visitId, @PathVariable Long petId, @Validated Visit visit,
		BindingResult result, @PathVariable Long ownerId) {
		if (result.hasErrors() || visitService.findById(visitId) == null) {
			return "pets/createOrUpdateVisitForm";
		}
		petService.findById(petId)
			.getVisits()
			.removeIf(v -> v.getId()
				.equals(visitId));
		visitService.deleteById(visitId);
		return "redirect:/owners/{ownerId}/pets/" + petId + "/visits/new";
	}
}
