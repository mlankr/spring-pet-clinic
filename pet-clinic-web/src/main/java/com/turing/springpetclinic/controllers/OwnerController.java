package com.turing.springpetclinic.controllers;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	// @RequestMapping({"", "/", "/index", "/index.html"})
	// public String listOwners(Model model) {
	// 	model.addAttribute("owners", ownerService.findAll());
	// 	return "owners/index";
	// }

	@RequestMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = ownerService.findById(ownerId);
		mav.addObject(owner);
		return mav;
	}

	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		if (owner.getLastname() == null) {
			owner = owner.toBuilder().lastname("").build();
		}

		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastname() + "%");

		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastname", "notFound", "not found");
			return "owners/findOwners";
		} else if (results.size() == 1) {
			// 1 owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			// multiple owners found
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}
}
