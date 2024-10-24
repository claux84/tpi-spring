package com.info.cooking_recipe_app.service.step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.info.cooking_recipe_app.domain.Ingredient;
import com.info.cooking_recipe_app.domain.Recipe;
import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;
import com.info.cooking_recipe_app.exceptions.ResourceNotFoundException;
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
        return stepRepository.findById(idStep).orElseThrow(() -> new ResourceNotFoundException("El paso con el id: "+ idStep + " no fue encontrado"));
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
        Step stepSaved =saveStep(newStep, stepCreateDto.recipeUuid(), stepCreateDto.ingredientsQuantity());
        return stepMapper.stepToStepDto(stepSaved);
    }

    @Override
    public boolean updateStepById(UUID idStep, StepUpdateDto stepUpdateDto) {
        if (stepRepository.existsById(idStep)) {
            Step stepFromDb = findStepById(idStep);
            Step stepUpdated = stepMapper.stepUdateDtoToStep(stepUpdateDto, stepFromDb);
            if (!!CollectionUtils.isEmpty(stepUpdateDto.ingredientsQuantity())) {
                stepUpdated.setIngredientsQuantity(saveIngredientList(stepUpdateDto.ingredientsQuantity(), stepUpdated.getIngredientsQuantity()));
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

    private Map<Ingredient, Long> saveIngredientList(Map<Long, Long> ingredientIdMap, Map<Ingredient,Long> ingredientListMap){
        ingredientIdMap.forEach((k,v) -> ingredientListMap
                        .put(ingredientService.findIngredientById(k), v));
        return ingredientListMap;
    } 

    @Override
    public Step saveStep(Step step, UUID idRecipe, Map<Long, Long> ingredientsQuantity) {
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(() -> new ResourceNotFoundException("La creceta con el id: "+ idRecipe + " no fue encontrada"));
        step.setRecipe(recipe);
        if (!CollectionUtils.isEmpty(ingredientsQuantity)) {
            Map<Ingredient,Long> newIngredientsList = new HashMap<>();
            step.setIngredientsQuantity(saveIngredientList(ingredientsQuantity, newIngredientsList));
        }
        return stepRepository.save(step);
    }

    

}
