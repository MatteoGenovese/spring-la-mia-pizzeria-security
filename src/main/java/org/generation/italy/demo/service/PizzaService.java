package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public void save(Pizza pizza)
	{
		pizzaRepository.save(pizza);
	}
	
	public List<Pizza> findAll(){
		return pizzaRepository.findAll();
	}
	
	public Optional<Pizza> getPizzaById(int id)
	{
		return pizzaRepository.findById(id);
	}
	
	public void delete(int id)
	{
		pizzaRepository.deleteById(id);
	}
	
	public List<Pizza> findByNameOrDescriptionContaining(String s)
	{
		return pizzaRepository.findByNameContainingOrDescriptionContaining(s,s);
	}
	

	


}
