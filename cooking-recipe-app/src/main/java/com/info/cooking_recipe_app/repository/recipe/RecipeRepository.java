package com.info.cooking_recipe_app.repository.recipe;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.cooking_recipe_app.domain.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

}
