package org.generation.italy.demo.pojo;




import java.util.List;

import org.generation.italy.demo.pojo.Interface.PriceableInt;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Pizza implements PriceableInt{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@NotNull(message="Pizza name must not be null")
	@Size(min=3, max=32, message="Pizza name must be min 3 charachters")
	@Column(length=32)
	private String name;
	
	
	@NotNull
	@NotEmpty(message = "Pizza description must contain something")
	@Column(length = 512)
	private String description;
	
	@NotNull
	@Min(value=1, message = "Pizza price must be greater than or equal to 1")
	private int price;

	@ManyToOne
	private Promoting promoting;
	
	
	@ManyToMany
	private List<Ingredient> ingredientList;


	public Pizza() { }
	public Pizza(String name, String description, int price) {

		setName(name);
		setDescription(description) ;
		setPrice(price);
	}
	
	public Pizza(String name, String description, int price, Promoting promoting) {

		this(name, description, price);
		
		setPromoting(promoting);
	}
	
	public Pizza(String name, String description, int price, List<Ingredient> ingredientList) {

		this(name, description, price);
		
		setIngredientList(ingredientList);
	}
	
	public Pizza(String name, String description, int price, Promoting promoting, List<Ingredient> ingredientList ) {
		
		this(name, description, price, promoting);
		
		setIngredientList(ingredientList);
		
	}
	
	public Promoting getPromoting() {
		return promoting;
	}
	
	public void setPromoting(Promoting promoting) {
		this.promoting = promoting;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}
	
	public void setIngredientList(List<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	public void addIngredient(Ingredient ingredient) {
		
		boolean finded = false;
		for (Ingredient i : getIngredientList()) {
			
			if (i.getId() == ingredient.getId())
				finded = true;
		}
		
		if (!finded)
			getIngredientList().add(ingredient);
	}
	
	@Override
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {

		return "\nid - "+getId()+
				"\nname - "+ getName()+
				"\ndescription - "+ getDescription()+
				"\nprice - "+ getPrice();
	}
	
	
	
	
	

}
