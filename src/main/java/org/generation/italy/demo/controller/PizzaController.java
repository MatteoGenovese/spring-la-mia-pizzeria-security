package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promoting;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromotingService;
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
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PromotingService promotingService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getPizzaList(Model model) {
		
		List<Pizza> pizzaList = pizzaService.findAll();
		model.addAttribute("pizzaList", pizzaList);
		
		return "pizza/index";
	}
	
	@GetMapping("/{id}")
	public String getPizzaById(@PathVariable("id") int id, Model model)
	{
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		
		Pizza pizza =optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza/show";
	}
	
	
	@GetMapping("/create")
	public String createPizza( Model model)
	{

		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List <Promoting> promotingList = promotingService.findAll();
		model.addAttribute("promotingList", promotingList);
		
		List <Ingredient> ingredientList = ingredientService.findAll();
		model.addAttribute("ingredientList", ingredientList);
		
		
		
		model.addAttribute("type", "create");
		model.addAttribute("h1text", "Create a new pizza for the list:");
		
		return "pizza/form";
	}
	
	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza,Error e, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		
		if (bindingResult.hasErrors())
		{
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/create";
		}
		
		try {
			
			pizzaService.save(pizza);
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("errors", ex.getMessage());
			return "redirect:/pizza/create";
		}
			
		
		return "redirect:/pizza";
	}
	
	@GetMapping("/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model,Error e, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{

		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/create";
		}
		
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		
		Pizza pizza =optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		model.addAttribute("type", "edit");
		model.addAttribute("h1text", "Update pizza:");
		
		
		return "pizza/form";
	}
	
	@PostMapping("/edit")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/create";
		}
		pizzaService.save(pizza);
		
		return "redirect:/pizza";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		
		pizzaService.delete(id);
		return "redirect:/pizza";
	}
	
	@GetMapping("/search")
	public String searchByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		

		List<Pizza> pizzaList = query == null || query.isEmpty()
							? pizzaService.findAll()
							: pizzaService.findByNameOrDescriptionContaining(query); 
		
		model.addAttribute("pizzaList", pizzaList);
		model.addAttribute("query", query);
		
		return "pizza/search";
	}

}
