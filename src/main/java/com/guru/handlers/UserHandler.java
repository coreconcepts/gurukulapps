package com.guru.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.guru.exceptions.UserNotFound;
import com.guru.model.Message;
import com.guru.model.User;
import com.guru.service.UserService;
import com.guru.validators.UserValidator;

@Controller
@RequestMapping(value="/user")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@RequestMapping(value="/abcd")
    public String showForm() {
        return "index";
    }
	
	@RequestMapping(value="/details")
	public @ResponseBody User getLoginDetails(@RequestParam String login) {
		User user = new User();
		for(User iUser: userService.findByLogin(login)){
			user=iUser;
		}
		return user;
	}
	
	@RequestMapping(value="/edituser")
	public @ResponseBody User edit(@RequestParam String login, @RequestParam String pass, @RequestParam String role, @RequestParam int enabled ) {
		User user = new User(login,pass,enabled,role);
		try {
			userService.update(user); 
			} catch (UserNotFound e) {
				user = new User("Error", "",0 , "");
			}catch(Exception e){
				e.printStackTrace();
			}
		return user;
	}

	

	@RequestMapping(value="/input", method=RequestMethod.GET)
	public ModelAndView newUserPage() {
		ModelAndView mav = new ModelAndView("user-input", "user", new User());
		Iterable<User> userList = userService.findAll();
		mav.addObject("users", (java.util.List<User>)userList);
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user,
			BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return new ModelAndView("user-input");
		
		ModelAndView mav = new ModelAndView();
		userService.create(user);
		mav.setViewName("redirect:/user/view/list");
		return mav;		
	}
	
	@RequestMapping(value="/view/list", method=RequestMethod.GET)
	public ModelAndView getList() {
		ModelAndView mav = new ModelAndView("user-list");
		Iterable<User> userList = userService.findAll();
		mav.addObject("users", (java.util.List<User>)userList);
		return mav;
	}
	
	
	
@RequestMapping(value="/list/{id}", method=RequestMethod.GET)
	public ModelAndView userPage(@PathVariable("id") String userName) {
		ModelAndView mav = new ModelAndView("user-list");
		List<User> userList = new ArrayList<User>(); 
		userService.findByLogin(userName);
		mav.addObject("userList", userList);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("user-edit");
		User user = userService.findById(id);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public ModelAndView editUser(@RequestParam String login,@ModelAttribute @Valid User user,
			BindingResult result,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws UserNotFound {
		
		if (result.hasErrors())
			return new ModelAndView("user-edit");
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "User was successfully updated.";

		userService.update(user);
		
		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws UserNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/index.html");		
		
		User user = userService.delete(id);
		String message = "The user "+user.getLogin()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
}
