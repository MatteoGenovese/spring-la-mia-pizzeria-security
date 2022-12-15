package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.repository.IngredientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	public void save(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}

	public void delete(int id) {
		ingredientRepository.deleteById(id);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}
	
	public Optional<Ingredient> getIngredientById(int id) {
		return ingredientRepository.findById(id);
	}
	
	public List<Ingredient> findByNameContaining(String name) {
		return ingredientRepository.findByNameContaining(name);
	}
	
	
	
	
	@Transactional
	public List<Ingredient> findAllWPizzaList() {
		List<Ingredient> ingredientList = ingredientRepository.findAll();
		
		for (Ingredient ingredient : ingredientList) {
			Hibernate.initialize(ingredient.getPizzaList());
		}
		
		return ingredientList;
		
	}
}