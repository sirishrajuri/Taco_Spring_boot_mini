package com.akkodis.codingchallenge.taco.services;

import com.akkodis.codingchallenge.taco.entity.Ingredient;
import com.akkodis.codingchallenge.taco.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public void deductIngredientQuantity(Long ingredientId, Integer quantityToDeduct) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Ingredient ID"));

        ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredient(Long ingredientId){
        return ingredientRepository.findById(ingredientId).orElse(null);
    }
}
