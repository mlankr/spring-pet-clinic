package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.PetTypeService;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
 * Created by Milan on 2023/07/09.
 */
@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

	private final OwnerService ownerService;
	private final PetService petService;
	private final PetTypeService petTypeService;

	public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable Long ownerId) {
		return ownerService.findById(ownerId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/new")
	public String initCreationForm(Owner owner, Model model, @PathVariable Long ownerId) {
		Pet pet = Pet.builder()
			.build();
		owner.setPets(Set.of(pet));
		pet = pet.toBuilder()
			.owner(owner)
			.build();
		model.addAttribute("pet", pet);
		return "pets/createOrUpdatePetForm";
	}

	@PostMapping("/new")
	public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model,
		@PathVariable Long ownerId) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPets()
			.contains(pet)) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		if (result.hasErrors()) {
			model.addAttribute("pet", pet);
			return "pets/createOrUpdatePetForm";
		} else {
			pet = pet.toBuilder()
				.owner(owner)
				.build();
			Pet savedPet = petService.save(pet);
			owner.setPets(Set.of(savedPet));
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping("/{petId}/edit")
	public String initUpdateForm(@PathVariable Long petId, Model model, @PathVariable Long ownerId) {
		model.addAttribute("pet", petService.findById(petId));
		return "pets/createOrUpdatePetForm";
	}

	@PostMapping("/{petId}/edit")
	public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model,
		@PathVariable Long ownerId, @PathVariable Long petId) {
		if (result.hasErrors()) {
			pet = pet.toBuilder()
				.owner(owner)
				.build();
			model.addAttribute("pet", pet);
			return "pets/createOrUpdatePetForm";
		} else {
			pet = pet.toBuilder()
				.owner(owner)
				.build();
			Pet updatedPet = petService.update(pet, petId);
			owner.setPets(Set.of(updatedPet));
			return "redirect:/owners/" + owner.getId();
		}
	}

	@RequestMapping("/{petId}/delete")
	public String deletePet(@PathVariable Long petId, @Validated Pet pet, BindingResult result,
		@PathVariable Long ownerId) {
		if (result.hasErrors() || petService.findById(petId) == null) {
			return "pets/createOrUpdateVisitForm";
		}

		Optional.ofNullable(ownerService.findById(ownerId))
			.ifPresent(o -> o.getPets()
				.removeIf(p -> p != null && p.getId()
					.equals(petId)));

		petService.deleteById(petId);
		return "redirect:/owners/" + ownerId;
	}

	@GetMapping("/*")
	public String notImplemented() {
		return "oops/notFound";
	}
}
