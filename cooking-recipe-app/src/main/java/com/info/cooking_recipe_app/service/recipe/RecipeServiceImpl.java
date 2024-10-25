package com.info.cooking_recipe_app.service.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.info.cooking_recipe_app.domain.Category;
import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.recipe.RecipeCreateDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeIngredientDto;
import com.info.cooking_recipe_app.dto.recipe.RecipeUpdateDto;
import com.info.cooking_recipe_app.dto.step.StepPlusIngredientsInRecipeDto;
import com.info.cooking_recipe_app.exceptions.ResourceNotFoundException;
import com.info.cooking_recipe_app.mappers.recipe.RecipeMapper;
import com.info.cooking_recipe_app.mappers.step.StepMapper;
import com.info.cooking_recipe_app.repository.recipe.RecipeRepository;
import com.info.cooking_recipe_app.service.category.CategoryService;
import com.info.cooking_recipe_app.service.step.StepService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;

    private RecipeMapper recipeMapper;

    private CategoryService categoryService;

    private StepService stepService;

    private StepMapper stepMapper;




    @Override
    public Recipe findRecipeById(UUID idRecipe) {
        return recipeRepository.findById(idRecipe)
                               .orElseThrow(() -> new ResourceNotFoundException("La receta con el id: "+ idRecipe + " no fue encontrada"));
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        
        return recipeRepository.findAll().stream()
                                .map(recipe -> recipeMapper.recipeToRecipeDto(recipe))
                                .toList();
    }

    @Override
    public RecipeDto getRecipeById(UUID idRecipe) {
        return recipeMapper.recipeToRecipeDto(findRecipeById(idRecipe));
    }

    @Override
    public RecipeDto createRecipe(RecipeCreateDto recipeCreateDto) {
        Recipe newRecipe = recipeMapper.recipeCreateDtoToRecipe(recipeCreateDto);
        Category category = categoryService.findCategoryById(recipeCreateDto.category());
        newRecipe.setCategory(category);
        Recipe recipeSaved = recipeRepository.save(newRecipe);
        
        if (!CollectionUtils.isEmpty(recipeCreateDto.stepsList())) {
            recipeCreateDto.stepsList().stream()
                                                .map(step -> stepService
                                                    .saveStep(stepMapper.stepCreateInRecipeToStep(step), recipeSaved.getId(), step.ingredientsQuantity()))
                                                .toList();
        } 
        return recipeMapper.recipeToRecipeDto(findRecipeById(recipeSaved.getId()));
    }
    @Override
    public boolean updateRecipeById(UUID idRecipe, RecipeUpdateDto recipeUpdateDto) {
        if (recipeRepository.existsById(idRecipe)) {
            Recipe recipe = findRecipeById(idRecipe);
            Recipe recipeUpdated = recipeMapper.toRecipe(recipeUpdateDto, recipe);
            recipeMapper.recipeToRecipeDto(recipeRepository.save(recipeUpdated));
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public boolean deleteRecipeById(UUID idRecipe) {

        if (recipeRepository.existsById(idRecipe)) {
            recipeRepository.deleteById(idRecipe);
            return true;
        } else {
            return false;
        }
    
    }

    @Override
    public RecipeIngredientDto getIngredientsInRecipeById(UUID idRecipe, UUID idStep) {
        Recipe recipeFromDb = findRecipeById(idRecipe);
        List<StepPlusIngredientsInRecipeDto> stepList = new ArrayList<>();
        if (idStep!=null) {
            Step stepFromDB = stepService.findStepById(idStep);
            if (idRecipe.equals(stepFromDB.getRecipe().getId())) {
                stepList.add(stepMapper.StepToStepPlusIngredientInRecipeDto(stepFromDB));
            } else {
                throw new ResourceNotFoundException("El paso indicado no pertenece a la receta enviada");
            }
        } else {
            stepList = recipeFromDb.getStepsList().stream().map(step -> stepMapper.StepToStepPlusIngredientInRecipeDto(step)).toList();
        }
        return recipeMapper.RecipeToRecipeIngredientDto(recipeFromDb, stepList);
    }

   


}
