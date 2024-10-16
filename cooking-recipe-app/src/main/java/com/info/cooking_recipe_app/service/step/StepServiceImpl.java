package com.info.cooking_recipe_app.service.step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;
import com.info.cooking_recipe_app.mappers.step.StepMapper;
import com.info.cooking_recipe_app.repository.recipe.RecipeRepository;
import com.info.cooking_recipe_app.repository.step.StepRepository;
import com.info.cooking_recipe_app.service.ingredient.IngredientService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StepServiceImpl implements StepService{

    StepRepository stepRepository;

    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    StepMapper stepMapper;

    @Override
    public Step findStepById(UUID idStep) {
        return stepRepository.findById(idStep).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<StepDto> getAllSteps() {
    
        return stepRepository.findAll().stream()
                            .map(step -> stepMapper.stepToStepDto(step))
                            .toList();
    }

    @Override
    public StepDto getStepById(UUID idStep) {
        return stepMapper.stepToStepDto(findStepById(idStep));
    }

    @Override
    public StepDto createStep(StepCreateDto stepCreateDto) {
        Step newStep = stepMapper.stepCreateDtoToStep(stepCreateDto);
        if (stepCreateDto.recipeUuid()!= null) {
            Recipe recipe = recipeRepository.findById(stepCreateDto.recipeUuid()).orElseThrow(NoSuchElementException::new);
            newStep.setRecipe(recipe);
        }
        if (!CollectionUtils.isEmpty(stepCreateDto.ingredientsQuantity())) {
            Map<Ingredient,Long> newIngredientsList = new HashMap<>();
            for(Map.Entry<Long,Long> entry: stepCreateDto.ingredientsQuantity().entrySet()){
                Ingredient ingredient = ingredientService.findIngredientById(entry.getKey());
                newIngredientsList.put(ingredient, entry.getValue());
            }
            newStep.setIngredientsQuantity(newIngredientsList);
   
        }

        return stepMapper.stepToStepDto(stepRepository.save(newStep));
    }

    @Override
    public boolean updateStepById(UUID idStep, StepUpdateDto stepUpdateDto) {
        if (stepRepository.existsById(idStep)) {
            Step stepFromDb = findStepById(idStep);
            Step stepUpdated = stepMapper.stepUdateDtoToStep(stepUpdateDto, stepFromDb);
            if (!stepUpdateDto.ingredientsQuantity().isEmpty()) {
                for(Map.Entry<Long,Long> entry: stepUpdateDto.ingredientsQuantity().entrySet()){
                    Ingredient ingredient = ingredientService.findIngredientById(entry.getKey());
                    stepUpdated.getIngredientsQuantity().put(ingredient, entry.getValue());
                }
            }
            stepRepository.save(stepUpdated);
            return true;
    
        } else {
            return false;
        }
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
