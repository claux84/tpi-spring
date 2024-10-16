package com.info.cooking_recipe_app.controller.step;

import org.springframework.web.bind.annotation.RestController;

import com.info.cooking_recipe_app.dto.step.StepCreateDto;
import com.info.cooking_recipe_app.dto.step.StepDto;
import com.info.cooking_recipe_app.dto.step.StepUpdateDto;
import com.info.cooking_recipe_app.service.step.StepService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/api/v1/step")
@AllArgsConstructor
@RestController
public class StepController {

    private StepService stepService;

    @GetMapping
    public List<StepDto> getAllSteps() {
        return stepService.getAllSteps();
    }

    @GetMapping("/{idStep}")
    public StepDto getStepById(@PathVariable("idStep") UUID idStep) {

        return stepService.getStepById(idStep);
    }
    
    
    @PostMapping
    public ResponseEntity<?> createStep(@RequestBody StepCreateDto stepCreateDto) {
        StepDto stepDto = stepService.createStep(stepCreateDto);
        
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(stepDto);
    }

    @PutMapping("/{idStep}")
    public ResponseEntity<?> putMethodName(@PathVariable("idStep") UUID idStep, @RequestBody StepUpdateDto stepUpdate) {
        boolean isStepUpdated = stepService.updateStepById(idStep, stepUpdate);
        if (isStepUpdated) {
            StepDto stepUpdated = getStepById(idStep);
            return ResponseEntity.ok().body(stepUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{idStep}")
    public ResponseEntity<?> deleteStepById(@PathVariable("idStep") UUID idStep){
        boolean isStepDeleted = stepService.deleteStepById(idStep);
        if (isStepDeleted) {
            return ResponseEntity.noContent()
                                 .build();
        } else {
            return ResponseEntity.notFound()
                                 .build();
        }
    }
    



}
