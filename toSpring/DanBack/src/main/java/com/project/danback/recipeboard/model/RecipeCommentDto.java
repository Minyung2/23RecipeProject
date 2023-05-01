package com.project.danback.recipeboard.model;

import java.sql.Date;

import lombok.Data;

@Data
public class RecipeCommentDto {
	private String comment_id;
	private String recipe_id;
	private String user_idx;
	private String comment_content;
	private Date comment_date;
	private String user_nickname;
}
