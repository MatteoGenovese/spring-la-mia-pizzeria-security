package org.generation.italy.demo.repository;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
	
	public List<Pizza> findByNameContainingOrDescriptionContaining(String name, String description);

}
