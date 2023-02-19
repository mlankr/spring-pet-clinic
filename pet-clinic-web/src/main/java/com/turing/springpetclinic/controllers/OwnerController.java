package com.turing.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Milan on 2023/02/19.
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {

    @RequestMapping({ "", "/", "/index", "/index.html"})
    public String listOwners() {
        return "owners/index";
    }
}
