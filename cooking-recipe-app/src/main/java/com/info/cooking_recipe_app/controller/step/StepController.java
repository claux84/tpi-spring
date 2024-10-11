package com.info.cooking_recipe_app.controller.step;

import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.service.step.StepService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/api/v1/step")
@AllArgsConstructor
@RestController
public class StepController {

    private StepService stepService;

    @GetMapping
    public List<StepDto> getAllSteps() {
        return stepService.getAllSteps();
    }
    
    @PostMapping
    public ResponseEntity<?> createStep(@RequestBody StepCreateDto stepCreateDto) {
        StepDto stepDto = stepService.createStep(stepCreateDto);
        
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(stepDto);
    }
    



}
