package com.project.danback.recipeboard.model;

import lombok.Data;

@Data
public class RecipeIngredientDto {
	private String recipe_id;
	private String ingredient_amount;
	private String ingredient_name;
}
