package com.acme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"/"})
public class GeneralController {

	
	public GeneralController(){
		super();
	}
	
	@RequestMapping({"/","/index"})
    public String showIndex(Model model) {
		model.addAttribute("activeMenu", "index");
        return "/common";
    }

}
