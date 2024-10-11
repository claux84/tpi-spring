package com.info.cooking_recipe_app.dto.step;

import java.util.UUID;

public record StepInRecipeDto(UUID id,
                              Long estimatedTime,
                              Boolean optional,
                              String description) {

}
