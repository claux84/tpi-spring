package com.info.cooking_recipe_app.dto.errors;


import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de un error generico"
)

public record ErrorDto(
                        @Schema(description = "Codigo de Error Http")
                        int statusCode,
                        @Schema(description = "Fecha y hora en la que sucedio el error")
                        LocalDateTime timestamp,
                        @Schema(description = "Mensaje del error")
                        String message,
                        @Schema(description = "Descripción del error")
                        String description
) {

}
