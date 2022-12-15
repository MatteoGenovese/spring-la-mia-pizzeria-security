package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredientList(Model model) {
		
		List<Ingredient> ingredientList = ingredientService.findAllWPizzaList();
		model.addAttribute("ingredientList", ingredientList);
		return "ingredient/index";
	}
	
	@GetMapping("/create")
	public String createIngredient( Model model)
	{
		
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzaList = pizzaService.findAll();
		model.addAttribute("pizzaList", pizzaList);
		
		model.addAttribute("type", "create");
		model.addAttribute("h1text", "Create a new ingredient for the list:");
		
		return "ingredient/form";
	}
	
	@PostMapping("/create")
	public String storeIngredient(
			@Valid @ModelAttribute("ingredient") Ingredient ingredient,
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes)
	{
		
		if (bindingResult.hasErrors())
		{
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingredients/create";
		}
		
		try {
			
			List<Pizza> pizzaList = ingredient.getPizzaList();
			for (Pizza pizza : pizzaList) {
				pizza.addIngredient(ingredient);
			}
			ingredientService.save(ingredient);
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("errors", ex.getMessage());
			return "redirect:/ingredients/create";
		}
		return "redirect:/ingredients";
	}
	
	
	
	
	
	
	
	@GetMapping("/{id}")
	public String getIngredientById(@PathVariable("id") int id, Model model)
	{
		Optional<Ingredient> optIngredient = ingredientService.getIngredientById(id);
		
		Ingredient ingredient =optIngredient.get();
		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredient/show";
	}
	
	
	
	
	@GetMapping("/edit/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model, Error e, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{

		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/ingredients/create";
		}
		
		Optional<Ingredient> optIngredient = ingredientService.getIngredientById(id);
		
		Ingredient ingredient =optIngredient.get();
		
		List<Pizza> pizzaList = pizzaService.findAll();
		model.addAttribute("pizzaList", pizzaList);
		
		System.out.println(ingredient);
		
		model.addAttribute("ingredient", ingredient);
		
		model.addAttribute("type", "edit");
		model.addAttribute("h1text", "Update ingredient:");
		
		
		return "ingredient/form";
	}
	
	@PostMapping("/edit")
	public String updateIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{

		
		if (bindingResult.hasErrors())
		{
			System.err.println(bindingResult.getAllErrors());
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingredients/create";
		}
		
		try {
			
			List<Pizza> pizzaList = ingredient.getPizzaList();
			for (Pizza pizza : pizzaList) {
				pizza.addIngredient(ingredient);
			}
			
			
			ingredientService.save(ingredient);
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("errors", ex.getMessage());
			return "redirect:/ingredients/edit";
		}
			
		
		return "redirect:/ingredients";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		
		ingredientService.delete(id);
		return "redirect:/ingredients";
	}
	
	@GetMapping("/search")
	public String searchByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		

		List<Ingredient> ingredientList = query == null || query.isEmpty()
							? ingredientService.findAll()
							: ingredientService.findByNameContaining(query); 
		
		model.addAttribute("ingredientList", ingredientList);
		model.addAttribute("query", query);
		
		return "ingredient/search";
	}
	


}

