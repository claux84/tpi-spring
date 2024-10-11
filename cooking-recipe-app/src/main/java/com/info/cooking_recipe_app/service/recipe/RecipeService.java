package com.info.cooking_recipe_app.service.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;

public interface RecipeService {

    List<RecipeDto> getAllRecipes();

    RecipeDto getRecipeById(UUID idRecipe);

    RecipeDto createRecipe(RecipeCreateDto recipeCreateDto);

    boolean deleteRecipe(UUID idRecipe);

}
