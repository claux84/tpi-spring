package com.info.cooking_recipe_app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.util.CollectionUtils;

import com.info.cooking_recipe_app.domain.enums.DifficultyEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;

    private Long servings;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.REMOVE, CascadeType.MERGE , CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Step> stepsList = new ArrayList<>() ;

    public Long getTotalPreparationTime(){
        Long totalPreparationTime = 0L;
            if (!CollectionUtils.isEmpty(this.getStepsList())) {
            for (Step step: this.getStepsList()) {
                if (!step.getOptional()) {
                    totalPreparationTime += step.getEstimatedTime();
                }
            }
        } 
       return totalPreparationTime;
    }


    
    

    

    

    
    



}
