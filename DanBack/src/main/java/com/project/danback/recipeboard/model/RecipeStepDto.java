package com.project.danback.recipeboard.model;

import lombok.Data;

@Data
public class RecipeStepDto {
	private String step_id;
	private String recipe_id;
	private String step_desc;
	private String step_image_url;
}
