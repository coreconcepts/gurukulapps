package com.guru.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventHandler {
	@Autowired
	EventRepository repository;

	@RequestMapping("/addevent")
	public String addEvent(@ModelAttribute Event event, Model model) {
		System.out.println(event);
		return "addevent";
	}

}
