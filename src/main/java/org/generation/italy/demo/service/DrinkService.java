package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
	
	@Autowired
	private DrinkRepository drinkRepository;
	
	public void save(Drink drink)
	{
		drinkRepository.save(drink);
	}
	
	public List<Drink> findAll(){
		return drinkRepository.findAll();
	}
	
	public Optional<Drink> getDrinkById(int id)
	{
		return drinkRepository.findById(id);
	}
	
	public void delete(int id)
	{
		drinkRepository.deleteById(id);
	}
	
	public List<Drink> findByNameOrDescriptionContaining(String s)
	{
		System.err.println(drinkRepository.findByNameContainingOrDescriptionContaining(s,s));
		return drinkRepository.findByNameContainingOrDescriptionContaining(s,s);
	}
	

	
	

}
