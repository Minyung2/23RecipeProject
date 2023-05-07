package com.project.danback.recipeboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.danback.recipeboard.dao.RecipeBoardMapper;
import com.project.danback.recipeboard.model.PageDto;
import com.project.danback.recipeboard.model.RecipeBoardDto;
import com.project.danback.recipeboard.model.RecipeCategoryTypeDto;
import com.project.danback.recipeboard.model.RecipeCommentDto;
import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeReviewDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import com.project.danback.recipeboard.model.ReviewImgDto;

@Service
public class RecipeBoardServiceImpl implements RecipeBoardService{

	@Autowired
	RecipeBoardMapper dao;

	
	@Override
	public int getCount(Map<String,Object> map) {
		return dao.getCount(map);
	}

	@Override
	public List<RecipeBoardDto> getList(PageDto pageDto) {
		return dao.getList(pageDto);
	}

	@Override
	public List<RecipeCategoryTypeDto> cate1List() {
		return dao.cate1List();
	}

	@Override
	public List<RecipeCategoryTypeDto> cate2List() {
		return dao.cate2List();
	}

	@Override
	public RecipeBoardDto detailView(String recipe_id) {
		return dao.detailView(recipe_id);
	}

	@Override
	public List<RecipeStepDto> stepDetailView(String recipe_id) {
		return dao.stepDetailView(recipe_id);
	}

	@Override
	public List<RecipeIngredientDto> ingreDetailView(String recipe_id) {
		return dao.ingreDetailView(recipe_id);
	}

	@Override
	public void save(RecipeBoardDto recipeBoardDto) {
		dao.save(recipeBoardDto);
	}

	@Override
	public void stepSave(RecipeStepDto recipeStepDto) {
		dao.stepSave(recipeStepDto);
	}
	@Override
	public void ingredientSave(RecipeIngredientDto recipeIngredientDto){
		dao.ingredientSave(recipeIngredientDto);
	}
	@Override
	public String getLastRecipeId() {
		return dao.getLastRecipeId();
	}


	@Override
	public List<RecipeReviewDto> reviewDetailView(String recipe_id) {
		return dao.reviewDetailView(recipe_id);
	}

	@Override
	public int getReviewCount(String recipe_id) {
		return dao.getReviewCount(recipe_id);
	}

	@Override
	public List<ReviewImgDto> reviewImgDetailView(String review_id) {
		return dao.reviewImgDetailView(review_id);
	}

	@Override
	public int getCommentCount(String recipe_id) {
		return dao.getCommentCount(recipe_id);
	}

	@Override
	public List<RecipeCommentDto> commentDetailView(String recipe_id) {
		return dao.commentDetailView(recipe_id);
	}

	@Override
	public void writeComment(RecipeCommentDto dto) {
		dao.writeComment(dto);
	}

}
