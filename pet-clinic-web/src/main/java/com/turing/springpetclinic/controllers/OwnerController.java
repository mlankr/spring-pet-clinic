package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.services.OwnerService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Milan on 2023/02/19.
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder()
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder()
				.build());
		return "owners/findOwners";
	}

	@GetMapping({"", "/"})
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		if (owner.getLastname() == null) {
			owner = owner.toBuilder()
					.lastname("")
					.build();
		}

		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastname() + "%");

		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastname", "notFound", "Not found");
			return "owners/findOwners";
		} else {
			// owners found
			model.addAttribute("selections", results);
			return "owners/allOwners";
		}
	}

	@RequestMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Optional<Owner> owner = Optional.of(ownerService.findById(ownerId));
		owner.ifPresent(mav::addObject);
		return mav;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("owner", Owner.builder()
				.build());
		return "owners/createOrUpdateOwnerForm";
	}

	@PostMapping("/new")
	public String processCreationForm(@Validated Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		}

		Owner savedOwner = ownerService.save(owner);
		return "redirect:/owners/" + savedOwner.getId();
	}

	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return "owners/createOrUpdateOwnerForm";
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result, @PathVariable Long ownerId) {
		if (result.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		}

		Owner updatedOwner = ownerService.update(owner, ownerId);
		return "redirect:/owners/" + updatedOwner.getId();
	}

	@RequestMapping("/{ownerId}/delete")
	public String deleteOwner(@PathVariable Long ownerId, @Validated Owner owner, BindingResult result) {
		if (result.hasErrors() || ownerService.findById(ownerId) == null) {
			return "owners/createOrUpdateOwnerForm";
		}
		ownerService.deleteById(ownerId);
		return "redirect:/owners";
	}
}
