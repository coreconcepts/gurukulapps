package com.guru.handlers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guru.exceptions.EventNotFound;
import com.guru.model.Event;
import com.guru.service.EventService;
import com.guru.validators.EventValidator;

@Controller
public class UtilsHandler {

	// Error page
	@RequestMapping("/customerror")
	public ModelAndView error(Principal user) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMessage", "No Access");
		model.setViewName("error");
		return model;
	}

}
