package com.info.cooking_recipe_app.service.step;

import java.util.List;
import java.util.UUID;

import com.info.cooking_recipe_app.domain.Step;
import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;

public interface StepService {

    Step findStepById(UUID idStep);

    List<StepDto> getAllSteps();

    StepDto getStepById(UUID idStep);

    StepDto createStep(StepCreateDto stepCreateDto);

    boolean updateStepById(UUID idStep, StepUpdateDto stepUpdateDto);

    boolean deleteStepById(UUID idStep);

    

}
