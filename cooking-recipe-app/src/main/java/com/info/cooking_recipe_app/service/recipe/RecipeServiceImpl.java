package com.info.cooking_recipe_app.service.recipe;

import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;


import org.springframework.stereotype.Service;


import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.mappers.recipe.RecipeMapper;
import com.info.cooking_recipe_app.repository.category.CategoryRepository;
import com.info.cooking_recipe_app.repository.recipe.RecipeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{

    RecipeRepository recipeRepository;

    RecipeMapper recipeMapper;

    CategoryRepository categoryRepository;

    @Override
    public List<RecipeDto> getAllRecipes() {
        
        return recipeRepository.findAll().stream()
                                .map(recipe -> recipeMapper.RecipeToRecipeDto(recipe))
                                .toList();
    }

    @Override
    public RecipeDto getRecipeById(UUID idRecipe) {
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(NoSuchElementException::new);
        RecipeDto recipeDto = recipeMapper.RecipeToRecipeDto(recipe);
        //return recipeMapper.RecipeToRecipeDto(recipeRepository.findById(UUID.fromString(idRecipe)).orElseThrow(Exception::new));
        return recipeDto;
    }

    @Override
    public RecipeDto createRecipe(RecipeCreateDto recipeCreateDto) {
        Recipe newRecipe = recipeMapper.RecipeCreateDtoToRecipe(recipeCreateDto);
        if (recipeCreateDto.category() != null) {
            Category category = categoryRepository.findById(recipeCreateDto.category()).orElseThrow(NoSuchElementException::new);
            newRecipe.setCategory(category);
        }

        return recipeMapper.RecipeToRecipeDto(recipeRepository.save(newRecipe));
    }

    @Override
    public boolean deleteRecipe(UUID idRecipe) {

        if (recipeRepository.existsById(idRecipe)) {
            recipeRepository.deleteById(idRecipe);
            return true;
        } else {
            return false;
        }
    
    }



}
