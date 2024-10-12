package com.info.cooking_recipe_app.service.step;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;

public interface StepService {

    List<StepDto> getAllSteps();

    StepDto getStepById(UUID stepId);

    StepDto createStep(StepCreateDto stepCreateDto);

    boolean deleteStepById(UUID stepId);
    

}
