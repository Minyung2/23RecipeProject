package com.project.danback.utility;

import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IngredientDtoWrapper {
    private List<RecipeIngredientDto> recipeIngredientList;
    public IngredientDtoWrapper(){
        recipeIngredientList = new ArrayList<>();
    }
}
