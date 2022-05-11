package com.example.demo.web.api;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Ingredient;
import com.example.demo.data.IngredientRepository;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="*")
public class IngredientController {
	private IngredientRepository  ingredientRepository;
	@Autowired
	EntityLinks entityLinks;
	public IngredientController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	@GetMapping
	public Iterable<Ingredient> getAllIngredients(){
		//ArrayList<Ingredient> list = new ArrayList<>();
		//list.add(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
		return ingredientRepository.findAll();
		//return list;
	}
	@GetMapping("/{id}")
	public Ingredient findById(String id) {
		Optional<Ingredient> optional =  ingredientRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
}
