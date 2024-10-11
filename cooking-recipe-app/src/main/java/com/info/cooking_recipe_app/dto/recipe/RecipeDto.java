package com.info.cooking_recipe_app.dto.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;
import com.info.cooking_recipe_app.dto.category.CategoryInRecipeDto;

import com.info.cooking_recipe_app.dto.step.StepInRecipeDto;

public record RecipeDto(UUID id,
                        String name,
                        String description,
                        DifficultyEnum difficulty,
                        Long servings,
                        CategoryInRecipeDto category,
                        List<StepInRecipeDto> stepsList
                        ) {

}
