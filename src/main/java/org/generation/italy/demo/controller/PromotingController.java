
package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promoting;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/promotings")
public class PromotingController {
	
	@Autowired
	private PromotingService promotingService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getPromotingList(Model model) {
		
		List<Promoting> promotingList = promotingService.findAllWPizzaList();
		model.addAttribute("promotingList", promotingList);
		return "promoting/index";
	}
	
	@GetMapping("/create")
	public String createPromoting( Model model)
	{
		
		Promoting promoting = new Promoting();
		model.addAttribute("promoting", promoting);
		
		List<Pizza> pizzaList = pizzaService.findAll();
		model.addAttribute("pizzaList", pizzaList);
		
		model.addAttribute("type", "create");
		model.addAttribute("h1text", "Create a new promoting for the list:");
		
		return "promoting/form";
	}
	
	@PostMapping("/create")
	public String storePromoting(
			@Valid @ModelAttribute("promoting") Promoting promoting,
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes)
	{
		
		if (bindingResult.hasErrors())
		{
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promotings/create";
		}
		
		try {
			
			List<Pizza> pizzaList = promoting.getPizzaList();
			for (Pizza pizza : pizzaList) {
				pizza.setPromoting(promoting);
			}
			
			
			promotingService.save(promoting);
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("errors", ex.getMessage());
			return "redirect:/promotings/create";
		}
			
		
		return "redirect:/promotings";
	}
	
	
	
	
	
	
	
	@GetMapping("/{id}")
	public String getPromotingById(@PathVariable("id") int id, Model model)
	{
		Optional<Promoting> optPromoting = promotingService.getPromotingById(id);
		
		Promoting promoting =optPromoting.get();
		
		model.addAttribute("promoting", promoting);
		
		return "promoting/show";
	}
	
	
	
	
	@GetMapping("/edit/{id}")
	public String editPromoting(@PathVariable("id") int id, Model model, Error e, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{

		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/promotings/create";
		}
		
		Optional<Promoting> optPromoting = promotingService.getPromotingById(id);
		
		Promoting promoting =optPromoting.get();
		
		List<Pizza> pizzaList = pizzaService.findAll();
		model.addAttribute("pizzaList", pizzaList);
		
		System.out.println(promoting);
		
		model.addAttribute("promoting", promoting);
		
		model.addAttribute("type", "edit");
		model.addAttribute("h1text", "Update promoting:");
		
		
		return "promoting/form";
	}
	
	@PostMapping("/edit")
	public String updatePromoting(@Valid @ModelAttribute("promoting") Promoting promoting, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{

		
		if (bindingResult.hasErrors())
		{
			System.err.println(bindingResult.getAllErrors());
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promotings/create";
		}
		
		try {
			
			List<Pizza> pizzaList = promoting.getPizzaList();
			for (Pizza pizza : pizzaList) {
				pizza.setPromoting(promoting);
			}
			
			
			promotingService.save(promoting);
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("errors", ex.getMessage());
			return "redirect:/promotings/edit";
		}
			
		
		return "redirect:/promotings";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		
		promotingService.delete(id);
		return "redirect:/promotings";
	}
	


}
