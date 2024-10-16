package com.info.cooking_recipe_app.dto.recipe;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

public record RecipeUpdateDto( String name,
                                 String description,
                                 DifficultyEnum difficulty,
                                 Long servings) {

}
