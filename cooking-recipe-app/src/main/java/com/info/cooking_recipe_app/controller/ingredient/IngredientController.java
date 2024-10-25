package com.info.cooking_recipe_app.controller.ingredient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.errors.ErrorDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientCreateDto;
import com.info.cooking_recipe_app.dto.ingredient.IngredientDto;
import com.info.cooking_recipe_app.service.ingredient.IngredientService;
import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
@RestController
public class IngredientController {

   
    private IngredientService ingredientService;

    @Operation(
            summary = "API REST para mostrar todas los ingredientes",
            description = "API REST que permite mostrar todos los ingredientes"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Los ingredientes estan presentados",
                    content = @Content(
                        schema = @Schema(implementation = IngredientDto.class)
                        )
            )
    })
    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @Operation(
            summary = "API REST para mostrar un ingrediente",
            description = "API REST que permite mostrar un ingrediente dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "El ingrediente ha sido encontrado",
                    content = @Content(
                        schema = @Schema(implementation = IngredientDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el ingrediente",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/{idIngredient}")
    public IngredientDto getIngredientById(@PathVariable("idIngredient") Long idIngredient){
        return ingredientService.getIngredientById(idIngredient);
    }

    @Operation(
        summary = "API REST para crear un ingrediente",
        description = "API REST que permite crear un ingrediente"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "El ingrediente fue creado",
                content = @Content(
                    schema = @Schema(implementation = IngredientDto.class)
                    )
        ),
        
    })
    @PostMapping
    public ResponseEntity<?> createIngredient(@Validated({Create.class, Default.class}) @RequestBody IngredientCreateDto ingredientCreateDto){
        IngredientDto ingredientDto = ingredientService.createIngredient(ingredientCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                              .body(ingredientDto);
    }

    @Operation(
            summary = "API REST para actualizar un ingrediente",
            description = "API REST que permite actualizar el ingrediente dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "El ingrediente fue actualizada",
                    content = @Content(
                        schema = @Schema(implementation = IngredientDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el ingrediente",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PutMapping("/{idIngredient}")
    public ResponseEntity<?> updateIngredientById(@PathVariable("idIngredient") Long idIngredient, @Valid @RequestBody IngredientCreateDto ingredientUpdate) {
        boolean isIngredientUpdated = ingredientService.updateIngredientById(idIngredient, ingredientUpdate);
        if (isIngredientUpdated) {
            IngredientDto ingredientUpdated = getIngredientById(idIngredient);
            return ResponseEntity.ok().body(ingredientUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    

    @Operation(
            summary = "API REST para eliminar un ingrediente",
            description = "API REST que permite eliminar un ingredeinte dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "El ingrediente fue eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el ingrediente",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @DeleteMapping("/{idIngredient}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable("idIngredient") Long idIngredient){
        boolean isIngredientDeleted = ingredientService.deleteIngredientById(idIngredient);
        if (isIngredientDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    




    



}
