package com.turing.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Milan on 2023/02/19.
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index() {
        return "index";
    }
}
