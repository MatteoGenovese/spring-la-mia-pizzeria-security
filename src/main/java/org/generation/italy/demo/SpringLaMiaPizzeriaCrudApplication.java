package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promoting;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.IngredientService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PromotingService promotingService;
	
	@Autowired
	private IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		Promoting pro1 = new Promoting("promozione estiva", LocalDate.now(), LocalDate.now());
		Promoting pro2 = new Promoting("promozione natalizia", LocalDate.now(), LocalDate.now());
		Promoting pro3 = new Promoting("promozione pasqua", LocalDate.now(), LocalDate.now());

		promotingService.save(pro1);
		promotingService.save(pro2);
		promotingService.save(pro3);
		
		Ingredient i1=new Ingredient("Pomodoro");
		Ingredient i2=new Ingredient("Mozzarella");
		Ingredient i3=new Ingredient("Prosciutto");
		Ingredient i4=new Ingredient("Salame");
		Ingredient i5=new Ingredient("Burrata");
		Ingredient i6=new Ingredient("Salame piccante");
		Ingredient i7=new Ingredient("Pesto alla Genovese");
		
		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		ingredientService.save(i4);
		ingredientService.save(i5);
		ingredientService.save(i6);
		ingredientService.save(i7);
		
		List<Ingredient> ingList1= Arrays.asList(new Ingredient[] {i1, i2});
		List<Ingredient> ingList2= Arrays.asList(new Ingredient[] {i5, i7});
		List<Ingredient> ingList3= Arrays.asList(new Ingredient[] {i1, i3, i2});
		List<Ingredient> ingList4= Arrays.asList(new Ingredient[] {i1, i4, i2});
		List<Ingredient> ingList5= Arrays.asList(new Ingredient[] {i1, i6, i2});
		
		Pizza p1 = new Pizza("Margherita", "Pomodoro e mozzarella", 7, pro1, ingList1);
		Pizza p2 = new Pizza("Marco", "Pomodoro, burrata, pesto",8, ingList2);
		Pizza p3 = new Pizza("Prosciutto", "Pomodoro, prosciutto e mozzarella", 9,pro2, ingList3);
		Pizza p4 = new Pizza("Salame", "Pomodoro, salame e mozzarella", 7, pro3, ingList4);
		Pizza p5 = new Pizza("Diavola", "Pomodoro, salame piccante e mozzarella", 19, ingList5);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		
		Drink d1 = new Drink("Moscow Mule", "Vodka, Lime, Ginger, Ice", 11);
		Drink d2 = new Drink("Hugo", "prosecco, elderflower syrup, seltz and mint leaves", 12);
		Drink d3 = new Drink("Spritz", "vino bianco frizzante (solitamente prosecco), un bitter come Aperol, Campari", 15);
		Drink d4 = new Drink("Gin Tonic", "Gin, acqua tonica", 7);
		Drink d5 = new Drink("Gin Lemon", "Gin Mare, lemon", 13);

		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		drinkService.save(d5);
		
		System.out.println( "----------------------------------------------------------");
		System.out.println( "-------READ-----------------------------------------------");
		System.out.println( "----------------------------------------------------------");
		
		List<Pizza> pizzaList = pizzaService.findAll();
		for (Pizza pizza : pizzaList) {
			System.out.println( pizza +"\n\t" + pizza.getPromoting());
		}
		
		System.out.println( "----------------------------------------------------------");
		System.out.println( "----------------------------------------------------------");
		System.out.println( "----------------------------------------------------------");

		List<Promoting> promotingList = promotingService.findAllWPizzaList();
		for (Promoting promoting : promotingList) {
			System.out.println( promoting );
			for (Pizza pizza : promoting.getPizzaList())
			{
				System.out.println("\t"+pizza);
			}
		}
		
		System.out.println( "----------------------------------------------------------");
		System.out.println( "----------------------------------------------------------");
		System.out.println( "----------------------------------------------------------");

	}

}
