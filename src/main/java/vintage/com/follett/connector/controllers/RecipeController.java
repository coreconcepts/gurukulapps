package com.follett.connector.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.follett.connector.util.DataBlock;

@Controller
public class RecipeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/recipe", method = RequestMethod.POST)
	public String addRecipe(Locale locale, Model model, DataBlock dataBlock, HttpServletRequest request) {
		request.getParameterValues("component");
		
		logger.info("Recipe Add! The client locale is {}.", locale);
		model.addAttribute(dataBlock);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "recipe/recipe-add";
	}

	

}
