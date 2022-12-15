package org.generation.italy.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Interface.PriceableInt;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PizzaService pizzaService;
	

	
	@GetMapping
	public String home() {
		
		return "home/index";
	}
	
	@GetMapping("/search")
	public String searchByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		

		List<Drink> drinkList = query == null 
							? drinkService.findAll()
							: drinkService.findByNameOrDescriptionContaining(query); 
		
		List<Pizza> pizzaList = query == null 
							? pizzaService.findAll()
							: pizzaService.findByNameOrDescriptionContaining(query); 
		
		model.addAttribute("drinkList", drinkList);
		model.addAttribute("pizzaList", pizzaList);
		model.addAttribute("query", query);
		
		return "home/search";
	}
	
	@GetMapping("/priceables")
	public String getPriceable(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		

		List <PriceableInt> priceables = new ArrayList<>();
		
		priceables.addAll(drinkService.findAll());
		priceables.addAll(pizzaService.findAll());

		priceables.sort((p1,p2) -> p1.getPrice()-p2.getPrice());
		model.addAttribute("priceables", priceables);
		model.addAttribute("query", query);
		
		return "home/priceables";
	}
	
	
	

}
