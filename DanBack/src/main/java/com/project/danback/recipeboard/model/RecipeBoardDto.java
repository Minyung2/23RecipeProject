package com.project.danback.recipeboard.model;

import lombok.Data;

@Data
public class RecipeBoardDto {
	private String recipe_id;
	private String user_idx;
	private String recipe_name;
	private String user_nickname;
	private String recipe_desc;
	private String recipe_people;
	private String recipe_time; 
	private String recipe_difficulty;
	private String recipe_image_url;
	private int recipe_visitcount;
	private String cate1;
	private String cate2;
	private String category_kind_name;
}
