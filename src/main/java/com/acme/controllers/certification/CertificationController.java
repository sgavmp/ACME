package com.acme.controllers.certification;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.hibernate.OptimisticLockException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Propagation;
import org.thymeleaf.util.Validate;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.user.Company;
import com.acme.model.user.User;
import com.acme.services.CertificationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping({ "/certification" })
public class CertificationController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CertificationService servicecertification;

	public CertificationController() {
		super();
	}

	@ModelAttribute("allCertifications")
	public List<Certification> allCertifications() {
		return servicecertification.getAllCertification();
	}

	// Muestra un listado con todos los certificados
	@RequestMapping({ "/**", "/list" })
	public String showAllCertificate(Model model) {
		model.addAttribute("activeMenu", "certification");
		return "/certification/listCertification";
	}
}
