package com.info.cooking_recipe_app.dto.errors;

import java.time.LocalDateTime;

public record ValidationErrorDto(
                                int statusCode,
                                LocalDateTime timestamp,
                                String fieldName,
                                String errorMessage,
                                String rejectedValue
) {

}
