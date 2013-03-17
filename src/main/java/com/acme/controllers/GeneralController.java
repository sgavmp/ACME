package com.acme.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.google.common.collect.Lists;


@Controller
@RequestMapping({"/"})
public class GeneralController {

	@Autowired
    private MessageSource messageSource;

	
	public GeneralController(){
		super();
	}
	
	@RequestMapping({"/","/index"})
    public String showIndex(Model model) {
		model.addAttribute("activeMenu", "index");
        return "/common";
    }
}
