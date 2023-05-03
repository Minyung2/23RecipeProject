package com.project.danback.recipeboard.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RecipeReviewDto {
	private String review_id;
	private String recipe_id;
	private String user_idx;
	private String user_nickname;
	private int review_rating;
	private String review_content;
	private Timestamp review_date;
}
