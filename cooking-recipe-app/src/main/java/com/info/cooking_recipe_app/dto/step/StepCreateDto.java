package com.info.cooking_recipe_app.dto.step;

import java.util.Map;
import java.util.UUID;

public record StepCreateDto(Long estimatedTime,
                            Boolean optional,
                            String description,
                            UUID recipeUuid,
                            Map<Long, Long> ingredientsList) {

}
