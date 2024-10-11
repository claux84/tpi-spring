package com.info.cooking_recipe_app.dto.category;

import java.util.List;
import java.util.UUID;


import com.info.cooking_recipe_app.dto.recipe.RecipeInCategoryDto;

public record CategoryDto(UUID id,
                          String name,
                          List<RecipeInCategoryDto> recipesList

) {

}
