package org.generation.italy.demo.repository;

import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer>{
	
	public List<Drink> findByNameContainingOrDescriptionContaining(String name, String description);

	
}
