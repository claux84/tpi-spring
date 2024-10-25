package com.info.cooking_recipe_app.dto.errors;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "DTO presentación de un error de validación"
)
public record ValidationErrorDto(
                                @Schema(description = "Codigo de Error Http")
                                int statusCode,
                                @Schema(description = "Fecha y hora en la que sucedio el error")
                                LocalDateTime timestamp,
                                @Schema(description = "Campo en el que sucedio el error")
                                String fieldName,
                                @Schema(description = "Mensaje del error")
                                String errorMessage,
                                @Schema(description = "Valor rechazado del error")
                                String rejectedValue
) {

}
