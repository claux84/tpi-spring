package com.info.cooking_recipe_app.service.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeIngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeUpdateDto;

public interface RecipeService {

    Recipe findRecipeById(UUID idRecipe);

    List<RecipeDto> getAllRecipes();

    RecipeDto getRecipeById(UUID idRecipe);

    RecipeDto createRecipe(RecipeCreateDto recipeCreateDto);

    boolean updateRecipeById(UUID idRecipe, RecipeUpdateDto recipeUpdateDto);

    boolean deleteRecipeById(UUID idRecipe);

    RecipeIngredientDto getIngredientsInRecipeById(UUID idRecipe, UUID idStep);

}
