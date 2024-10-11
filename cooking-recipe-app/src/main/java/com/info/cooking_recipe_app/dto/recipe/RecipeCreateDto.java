package com.info.cooking_recipe_app.dto.recipe;

import java.util.UUID;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

public record RecipeCreateDto(String name,
                              String description,
                              DifficultyEnum difficulty,
                              Long servings,
                              UUID category) {
} 
