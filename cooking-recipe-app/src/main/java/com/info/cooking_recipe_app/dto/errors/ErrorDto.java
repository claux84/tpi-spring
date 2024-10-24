package com.info.cooking_recipe_app.dto.errors;


import java.time.LocalDateTime;

public record ErrorDto(
                        int statusCode,
                        LocalDateTime timestamp,
                        String message,
                        String description
) {

}
