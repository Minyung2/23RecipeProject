package com.project.danback.recipeboard.model;

import lombok.Data;

import java.util.List;

@Data
public class RecipeIngredientDto {
	private String recipe_id;
	private String ingredient_amount;
	private String ingredient_name;
}
