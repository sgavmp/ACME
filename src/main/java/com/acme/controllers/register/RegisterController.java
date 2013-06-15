package com.acme.controllers.register;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/register" })
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "**", method = RequestMethod.GET)
	public String createRegister(Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {
		User u = userService.getUserByUsername(request.getUserPrincipal().getName());
		if (u.getRoles().containsKey(UserType.ROLE_ADMIN))
			return "redirect:/acme/admin/register/";
		else if (u.getRoles().containsKey(UserType.ROLE_CUSTOMER))
			return "redirect:/acme/customer/register/";
		else 
			throw new AccessDeniedException("exception.access.denied");
	}

}
