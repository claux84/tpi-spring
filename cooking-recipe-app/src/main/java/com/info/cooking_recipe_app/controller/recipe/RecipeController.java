package com.info.cooking_recipe_app.controller.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeIngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeUpdateDto;
import com.info.cooking_recipe_app.service.recipe.RecipeService;
import com.info.cooking_recipe_app.validator.groups.ValidatorGroups.Create;

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

    @GetMapping()
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{idRecipe}")
    public RecipeDto getRecipeById(@PathVariable("idRecipe") UUID idRecipe) {
        return recipeService.getRecipeById(idRecipe);
    }

    @GetMapping("/ingredient")
    public RecipeIngredientDto getIngredientsInRecipeById(
                @RequestParam(required = true, name = "idRecipe") UUID idRecipe,
                @RequestParam(required = false, name = "idStep")UUID idStep) {
        return recipeService.getIngredientsInRecipeById(idRecipe, idStep);
    }

    @PostMapping()
    public ResponseEntity<?> createRecipe(@Validated({Default.class, Create.class }) @RequestBody RecipeCreateDto recipeCreateDto) {

        RecipeDto recipeDto = recipeService.createRecipe(recipeCreateDto);
    
        
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(recipeDto);
    }

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
