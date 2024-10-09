package com.info.cooking_recipe_app.repository.step;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.cooking_recipe_app.domain.Step;

public interface StepRepository extends JpaRepository<Step, UUID>{

}
