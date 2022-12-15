package org.generation.italy.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table
public class Promoting {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	@NotNull(message= "title has to exist")
	@NotBlank(message= "title can't be empty")
	private String title;
	
	@Column
	@NotNull(message= "startDate has to exist")
	private LocalDate startDate;
	
	@Column
	@NotNull(message= "endDate has to exist")
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "promoting", cascade=CascadeType.REMOVE)
	private List<Pizza> pizzaList;
	
	public Promoting() { }
	public Promoting(String title, LocalDate startDate,LocalDate endDate) {
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDate getStartDate() {
		
			return startDate;
	}


	public void setStartDate(LocalDate startDate) {

		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
			return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	



	public List<Pizza> getPizzaList() {
		return pizzaList;
	}
	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}
	
	@Override
	public String toString() {
		return "\n (" + 
				getId()+") "+ 
				getTitle() + " - " +
				getStartDate() + " ~ " +
				getEndDate();
	}
	
	
	
}
