package com.info.cooking_recipe_app.controller.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.service.recipe.RecipeService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RequestMapping("/api/v1/recipe")
@AllArgsConstructor
@RestController
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{idRecipe}")
    public RecipeDto getRecipeById(@PathVariable("idRecipe") UUID idRecipe) {
        return recipeService.getRecipeById(idRecipe);
    }
    

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody RecipeCreateDto recipeCreateDto) {

        RecipeDto recipeDto = recipeService.createRecipe(recipeCreateDto);
    
        
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(recipeDto);
    }

    @DeleteMapping("/{idRecipe}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("idRecipe") UUID idRecipe){
        boolean isRecipeDeleted = recipeService.deleteRecipe(idRecipe);
        if (isRecipeDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    

}
