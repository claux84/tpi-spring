package com.info.cooking_recipe_app.dto.recipe;


import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.step.StepPlusIngredientsInRecipeDto;

public record RecipeIngredientDto(UUID id,
                                  String name,
                                  List<StepPlusIngredientsInRecipeDto> stepsList
                                  ) {

}
