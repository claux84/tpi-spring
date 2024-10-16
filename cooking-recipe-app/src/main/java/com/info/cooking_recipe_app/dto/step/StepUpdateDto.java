package com.info.cooking_recipe_app.dto.step;

import java.util.Map;

public record StepUpdateDto(Long estimatedTime,
                            Boolean optional,
                            String description,
                            Map< Long, Long> ingredientsQuantity ) {

}
