package com.turing.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Milan on 2023/02/19.
 */
@Controller
public class IndexController {

	@CrossOrigin(origins = "*")
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String index() {
		return "index";
	}

	@RequestMapping("/oops")
	public String error() {
		return "oops/index";
	}

	@GetMapping("/*")
	public String notImplemented() {
		return "oops/notFound";
	}
}
