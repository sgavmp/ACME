package com.acme.controllers.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.services.UtilService;

@Controller
@RequestMapping({ "/admin/util" })
public class AdminUtilController {

	@Autowired
	private UtilService serviceUtil;

	// Muestra el menu de opciones
	@RequestMapping({ "", "/**" })
	public String showUtils(Model model) {
		return "/util/mainUtil";
	}

	// Realiza el indexado
	@SuppressWarnings("finally")
	@RequestMapping(value = "/index")
	public String doIndex(Model model) {
		try {
			serviceUtil.createIndexSearch();
		} catch (InterruptedException e) {
			model.addAttribute("error", "exception.index.nocreate");
		} finally {
			model.addAttribute("info", "util.doingindex");
			return "/util/mainUtil";
		}
	}
}
