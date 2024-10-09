package com.info.cooking_recipe_app.repository.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.cooking_recipe_app.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
