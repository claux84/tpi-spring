package com.info.cooking_recipe_app.controller.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.info.cooking_recipe_app.dto.errors.ErrorDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeIngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeUpdateDto;
import com.info.cooking_recipe_app.service.recipe.RecipeService;
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
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;







@RequestMapping("/api/v1/recipe")
@AllArgsConstructor
@RestController
public class RecipeController {

    private RecipeService recipeService;


    @Operation(
            summary = "API REST para mostrar todas las recetas",
            description = "API REST que permite mostrar todas las recetas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Las recetas estan presentadas",
                    content = @Content(
                        schema = @Schema(implementation = RecipeDto.class)
                        )
            )
    })
    @GetMapping()
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @Operation(
            summary = "API REST para mostrar una receta",
            description = "API REST que permite mostrar una receta y sus pasos dado el id de la receta"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "La receta ha sido encontrada",
                    content = @Content(
                        schema = @Schema(implementation = RecipeDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la receta",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/{idRecipe}")
    public RecipeDto getRecipeById(@PathVariable("idRecipe") UUID idRecipe) {
        return recipeService.getRecipeById(idRecipe);
    }

    @Operation(
            summary = "API REST para mostrar los ingredientes de una receta",
            description = "API REST que permite mostrar los ingredientes de una receta o los ingredientes de uno de los pasos de una receta"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "La receta ha sido encontrada",
                content = @Content(
                    schema = @Schema(implementation = RecipeIngredientDto.class)
                )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el recurso",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/ingredient")
    public RecipeIngredientDto getIngredientsInRecipeById(
                @RequestParam(required = true, name = "idRecipe") UUID idRecipe,
                @RequestParam(required = false, name = "idStep")UUID idStep) {
        return recipeService.getIngredientsInRecipeById(idRecipe, idStep);
    }

    @Operation(
            summary = "API REST para crear una receta",
            description = "API REST que permite crear una receta"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "La receta fue creada",
                    content = @Content(
                        schema = @Schema(implementation = RecipeDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la categoria",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PostMapping()
    public ResponseEntity<?> createRecipe(@Validated({Default.class, Create.class }) @RequestBody RecipeCreateDto recipeCreateDto) {

        RecipeDto recipeDto = recipeService.createRecipe(recipeCreateDto);
    
        
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(recipeDto);
    }

    @Operation(
            summary = "API REST para actualizar una receta",
            description = "API REST que permite actualizar una receta dada su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "La receta fue actualizada",
                    content = @Content(
                        schema = @Schema(implementation = RecipeDto.class)
                        )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la receta",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @PutMapping("/{idRecipe}")
    public ResponseEntity<?> updateRecipeById(@PathVariable("idRecipe") UUID idRecipe, @Valid @RequestBody RecipeUpdateDto recipeUpdateDto) {
        boolean isRecipeUdated = recipeService.updateRecipeById(idRecipe, recipeUpdateDto);
        if (isRecipeUdated) {
            RecipeDto recipeUpdated = getRecipeById(idRecipe);
            return ResponseEntity.ok().body(recipeUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
        
        
    }

    @Operation(
            summary = "API REST para eliminar una receta",
            description = "API REST que permite eliminar una receta dado su id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "La receta fue eliminada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro la receta",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @DeleteMapping("/{idRecipe}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable("idRecipe") UUID idRecipe){
        boolean isRecipeDeleted = recipeService.deleteRecipeById(idRecipe);
        if (isRecipeDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    

}
