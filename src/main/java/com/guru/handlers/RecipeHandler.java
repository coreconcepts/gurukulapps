package com.guru.handlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.guru.exceptions.RecipeNotFound;
import com.guru.model.Component;
import com.guru.model.Recipe;
import com.guru.service.RecipeService;
import com.guru.validators.RecipeValidator;

@Controller
@RequestMapping(value = "/recipe")
public class RecipeHandler {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeValidator recipeValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
		binder.setValidator(recipeValidator);
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String showForm(Recipe recipe) {
		return "recipe-input";
	}
	@RequestMapping(value="/input/changecomponents", params={"addRow"})
	public String addRow(final Recipe recipe, final BindingResult bindingResult) {
	    recipe.getComponents().add(new Component());
	    return "recipe-input";
	}

	@RequestMapping(value="input/changecomponents", params={"removeRow"})
	public String removeRow(
			final Recipe recipe, final BindingResult bindingResult, 
	        final HttpServletRequest req) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    recipe.removeComponent(rowId);
	    return "recipe-input";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView createNewRecipe(@ModelAttribute @Valid Recipe recipe,
			BindingResult result, final RedirectAttributes redirectAttributes,final HttpServletRequest req) {

		if(req.getParameter("removeRow")!=null){
			final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		    recipe.removeComponent(rowId.intValue());
		    return new ModelAndView("recipe-input");
		}
		
		if(req.getParameter("addRow")!=null){
			recipe.getComponents().add(new Component());
		    return new ModelAndView("recipe-input");
		}
		
		if (result.hasErrors()){
			ModelAndView mnv = new ModelAndView("recipe-input");
			mnv.getModelMap().addAttribute("recipe", recipe);
			return mnv;
			
		}
		
		recipeService.create(recipe);
		return new ModelAndView("recipe-input");
		//return new ModelAndView("redirect:/recipe/list");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView recipeListPage() {
		ModelAndView mav = new ModelAndView("recipe-list");
		Iterable<Recipe> recipeList = recipeService.findAll();
		mav.addObject("recipes", (java.util.List<Recipe>) recipeList);
		return mav;
	}

	@RequestMapping(value = "/details")
	public @ResponseBody Recipe getLoginDetails(@RequestParam String id) {
		Recipe recipe = recipeService.findById(Integer.parseInt(id));
		return recipe;
	}

	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public ModelAndView recipePage(@PathVariable("id") String recipeName) {
		ModelAndView mav = new ModelAndView("recipe-list");
		List<Recipe> recipeList = new ArrayList<Recipe>();
		recipeService.findByRecipeName(recipeName);
		mav.addObject("recipeList", recipeList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editRecipePage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("recipe-edit");
		Recipe recipe = recipeService.findById(id);
		mav.addObject("recipe", recipe);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editRecipe(@ModelAttribute @Valid Recipe recipe,
			BindingResult result, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws RecipeNotFound {

		if (result.hasErrors())
			return new ModelAndView("recipe-edit");

		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Recipe was successfully updated.";

		recipeService.update(recipe);

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteRecipe(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws RecipeNotFound {

		ModelAndView mav = new ModelAndView("redirect:/index.html");

		Recipe recipe = recipeService.delete(id);
		String message = "The recipe " + recipe.getRecipeName()
				+ " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

}
