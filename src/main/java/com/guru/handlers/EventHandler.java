package com.guru.handlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guru.exceptions.EventNotFound;
import com.guru.model.Event;
import com.guru.model.User;
import com.guru.service.EventService;
import com.guru.validators.EventValidator;

@Controller
@RequestMapping(value="/event")
public class EventHandler {
	
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventValidator eventValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
		binder.setValidator(eventValidator);
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
    public String showForm(Event event) {
        return "event-input";
    }
	
	@RequestMapping(value="/abc")
    public String showForm() {
        return "index";
    }
	

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewEvent(@Valid Event event,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("event-input");
		
		ModelAndView mav = new ModelAndView();
		//String message = "New event "+event.getEventName()+" was successfully created.";
		
		eventService.create(event);
		
		mav.setViewName("redirect:/event/list");
				
		//redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView eventListPage() {
		ModelAndView mav = new ModelAndView("event-list");
		Iterable<Event> eventList = eventService.findAll();
		mav.addObject("events", (java.util.List<Event>)eventList);
		return mav;
	}
	
	
	
	@RequestMapping(value="/details")
	public @ResponseBody Event getLoginDetails(@RequestParam String id) {
		Event event = eventService.findById(Integer.parseInt(id));
		return event;
	}
	@RequestMapping(value="/list/{id}", method=RequestMethod.GET)
	public ModelAndView eventPage(@PathVariable("id") String eventName) {
		ModelAndView mav = new ModelAndView("event-list");
		List<Event> eventList = new ArrayList<Event>(); 
		eventService.findByEventName(eventName);
		mav.addObject("eventList", eventList);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editEventPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("event-edit");
		Event event = eventService.findById(id);
		mav.addObject("event", event);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editEvent(@ModelAttribute @Valid Event event,
			BindingResult result,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws EventNotFound {
		
		if (result.hasErrors())
			return new ModelAndView("event-edit");
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Event was successfully updated.";

		eventService.update(event);
		
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteEvent(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws EventNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		Event event = eventService.delete(id);
		String message = "The event "+event.getEventName()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
}
