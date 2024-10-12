package com.info.cooking_recipe_app.service.step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.mappers.step.StepMapper;
import com.info.cooking_recipe_app.repository.ingredient.IngredientRepository;
import com.info.cooking_recipe_app.repository.recipe.RecipeRepository;
import com.info.cooking_recipe_app.repository.step.StepRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StepServiceImpl implements StepService{

    StepRepository stepRepository;

    RecipeRepository recipeRepository;

    IngredientRepository ingredientRepository;

    StepMapper stepMapper;

    @Override
    public List<StepDto> getAllSteps() {
    
        return stepRepository.findAll().stream()
                            .map(step -> stepMapper.stepToStepDto(step))
                            .toList();
    }

    @Override
    public StepDto getStepById(UUID stepId) {
        Step step = stepRepository.findById(stepId).orElseThrow(NoSuchElementException::new);
        return stepMapper.stepToStepDto(step);
    }

    @Override
    public StepDto createStep(StepCreateDto stepCreateDto) {
        Step newStep = stepMapper.stepCreateDtoToStep(stepCreateDto);
        if (stepCreateDto.recipeUuid()!= null) {
            Recipe recipe = recipeRepository.findById(stepCreateDto.recipeUuid()).orElseThrow(NoSuchElementException::new);
            newStep.setRecipe(recipe);
        }
        if (!stepCreateDto.ingredientsList().isEmpty()) {
            Map<Long,Ingredient> newIngredientsList = new HashMap<>();
            for(Map.Entry<Long,Long> entry: stepCreateDto.ingredientsList().entrySet()){
                Ingredient ingredient = ingredientRepository.findById(entry.getValue()).orElseThrow(NoSuchElementException::new);
                newIngredientsList.put(entry.getKey(), ingredient);
            }
            newStep.setIngredientsList(newIngredientsList);
   
        }

        return stepMapper.stepToStepDto(stepRepository.save(newStep));
    }

    @Override
    public boolean deleteStepById(UUID stepId) {
        if (stepRepository.existsById(stepId)) {
            stepRepository.deleteById(stepId);
            return true;
        } else {
            return false;
        }
    }

}
