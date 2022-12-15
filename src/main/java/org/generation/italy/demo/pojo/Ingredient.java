package org.generation.italy.demo.pojo;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "name has not to be empty")
	@Column(unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "ingredientList")
	private List<Pizza> pizzaList;
	

	public Ingredient() { };
	
	public Ingredient(String name) {
		setName(name);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Pizza> getPizzaList() {
		return pizzaList;
	}
	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}
	@Override
	public String toString() {
		return "(" + getId() + ") " + getName();
	}
}

