package com.project.danback.recipeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.danback.recipeboard.model.PageDto;
import com.project.danback.recipeboard.model.RecipeBoardDto;
import com.project.danback.recipeboard.model.RecipeCategoryTypeDto;
import com.project.danback.recipeboard.model.RecipeCommentDto;
import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeReviewDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import com.project.danback.recipeboard.model.ReviewImgDto;

@Mapper
public interface RecipeBoardMapper {
	int getCount(Map<String,Object> map);
	List<RecipeBoardDto> getList(PageDto pageDto);
	List<RecipeCategoryTypeDto> cate1List();
	List<RecipeCategoryTypeDto> cate2List();
	
	RecipeBoardDto detailView(String recipe_id);
	List<RecipeStepDto> stepDetailView(String recipe_id);
	List<RecipeIngredientDto> ingreDetailView(String recipe_id);
	List<RecipeReviewDto> reviewDetailView(String recipe_id);
	int getReviewCount(String recipe_id);
	List<ReviewImgDto> reviewImgDetailView(String recipe_id);
	int getCommentCount(String recipe_id);
	List<RecipeCommentDto> commentDetailView(String recipe_id);
	
	void writeComment(RecipeCommentDto dto);
}
