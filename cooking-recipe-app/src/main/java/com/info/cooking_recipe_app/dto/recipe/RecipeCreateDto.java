package com.info.cooking_recipe_app.dto.recipe;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;

public record RecipeCreateDto(String name,
                              String description,
                              DifficultyEnum difficulty,
                              Long servings,
                              UUID category,
                              List<StepCreateDto> stepsList) {
} 
