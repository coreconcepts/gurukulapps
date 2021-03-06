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
import com.guru.model.RecipeEnv;
import com.guru.model.User;
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
		//return new ModelAndView("recipe-input");
		return new ModelAndView("redirect:/recipe/list");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView recipeListPage() {
		ModelAndView mav = new ModelAndView("recipe-list");
		Iterable<Recipe> recipeList = recipeService.findAll();
		mav.addObject("recipes", (java.util.List<Recipe>) recipeList);
		return mav;
	}

	@RequestMapping(value = "/details")
	public @ResponseBody RecipeEnv getLoginDetails(@RequestParam String id) {
		Recipe recipe = recipeService.findById(Integer.parseInt(id));
		RecipeEnv recipeEnv = new RecipeEnv();
		recipeEnv.setEnvRPersonCount("<input id=\"rcpPersonCount\""  + "value =\""+ recipe.getPersonCount().intValue()+"\" type=\"hidden\" />");  //rcpPersonCount
		recipeEnv.setRecipe(recipe);
		Object[] objArray = recipe.getComponents().toArray();
		Component[] componentArray = new Component[10];
		int i =0;
		for(Object obj: objArray){
			componentArray[i] = (Component)objArray[i];
			//'<input class="form-control" type="text" />';
			componentArray[i].setName("<td><input id=\"compName_" + componentArray[i].getQuantity()+"\" disabled=\"disabled\" class=\"form-control\"" +" type=\"text\"" +" value=\""+componentArray[i].getName()+"\"" +"/></td>");
			componentArray[i].setQuantity("<td><input id=\"qty_" + componentArray[i].getQuantity()+"\" disabled=\"disabled\" class=\"form-control\"" +" type=\"text\"" +" value=\""+componentArray[i].getQuantity()+"\"" +"/></td>"+ 
					"<td><input id=\"cqty_" + componentArray[i].getQuantity()+"\" class=\"form-control\"" +" type=\"text\"" +" value=\""+componentArray[i].getQuantity()+"\"" +"/></td>");
			componentArray[i].setUnit("<td><input id=\"compUnit_" + componentArray[i].getUnit()+"\" disabled=\"disabled\" class=\"form-control\"" +" type=\"text\"" +" value=\""+componentArray[i].getUnit()+"\"" +"/></td>");
			
			i++;
		}
		recipeEnv.setAllComponents(componentArray);
		recipe.setComponents(null);
		return recipeEnv;
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

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//public @ResponseBody User edit(@RequestParam String login, @RequestParam String pass, @RequestParam String role, @RequestParam int enabled ) {
	public @ResponseBody String deleteRecipe(@RequestParam Integer recipeId
			) throws RecipeNotFound {

		ModelAndView mav = new ModelAndView("redirect:/recipe/list");
		
		Recipe recipe = recipeService.delete(recipeId);
		String message = "The recipe " 
				+ " was successfully deleted.";
		return message;
	}

}
