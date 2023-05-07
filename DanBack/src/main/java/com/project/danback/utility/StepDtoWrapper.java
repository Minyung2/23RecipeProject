package com.project.danback.utility;

import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class StepDtoWrapper {

    private List<RecipeStepDto> recipeStepList;
    public StepDtoWrapper() {
        recipeStepList = new ArrayList<>();
    }
}
