package com.akkodis.codingchallenge.taco;

import com.akkodis.codingchallenge.taco.entity.Ingredient;
import com.akkodis.codingchallenge.taco.entity.Taco;
import com.akkodis.codingchallenge.taco.repository.IngredientRepository;
import com.akkodis.codingchallenge.taco.repository.TacoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.PrivateKey;
import java.util.*;

@SpringBootApplication
public class TacoApplication {

	@Autowired
	private TacoRepository tacoRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@PostConstruct()
	public void init(){
		List<Taco> listOfTaco = new ArrayList<>();
		listOfTaco.add(new Taco(1L,"indiantaco",100,20));
		listOfTaco.add(new Taco(2L,"mexicantaco",50,200));
		listOfTaco.add(new Taco(3L,"americantaco",20,50));
		tacoRepository.saveAll(listOfTaco);

		List<Ingredient> listOfIngredients = new ArrayList<>();
		listOfIngredients.add(new Ingredient(1L,"salsa",5));
		listOfIngredients.add(new Ingredient(2L,"guacamole",4));
		ingredientRepository.saveAll(listOfIngredients);

	}

	public static void main(String[] args) {
		SpringApplication.run(TacoApplication.class, args);
	}

}
