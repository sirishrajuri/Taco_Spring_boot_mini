package com.akkodis.codingchallenge.taco.repository;

import com.akkodis.codingchallenge.taco.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
