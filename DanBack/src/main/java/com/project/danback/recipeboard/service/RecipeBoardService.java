package com.project.danback.recipeboard.service;

import java.util.List;
import java.util.Map;

import com.project.danback.recipeboard.model.PageDto;
import com.project.danback.recipeboard.model.RecipeBoardDto;
import com.project.danback.recipeboard.model.RecipeCategoryTypeDto;
import com.project.danback.recipeboard.model.RecipeCommentDto;
import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeReviewDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import com.project.danback.recipeboard.model.ReviewImgDto;

public interface RecipeBoardService {
	// 레시피 게시판 조건별 Count
	int getCount(Map<String,Object> map);
	// 레시피 게시판 조건별 리스트
	List<RecipeBoardDto> getList(PageDto pageDto);

	// 레시피 게시판 카테고리 리스트
	List<RecipeCategoryTypeDto> cate1List();
	List<RecipeCategoryTypeDto> cate2List();

	// 레시피 게시판 글 상세보기
	RecipeBoardDto detailView(String recipe_id);
	// 레시피 게시판 글 상세보기 조리 과정
	List<RecipeStepDto> stepDetailView(String recipe_id);
	// 레시피 게시판 글 상세보기 재료 정보
	List<RecipeIngredientDto> ingreDetailView(String recipe_id);
	// 레시피 게시판 글 쓰기

	void save(RecipeBoardDto recipeBoardDto);
	void stepSave(RecipeStepDto recipeStepDto);
	void ingredientSave(RecipeIngredientDto recipeIngredientDto);

	String getLastRecipeId();


	// 레시피 게시판 글 상세보기 리뷰 Count
	int getReviewCount(String recipe_id);
	// 레시피 게시판 글 상세보기 리뷰 상세보기
	List<RecipeReviewDto> reviewDetailView(String recipe_id);
	// 레시피 게시판 글 상세보기 리뷰 이미지
	List<ReviewImgDto> reviewImgDetailView(String recipe_id);

	// 글 상세보기 댓글 Count
	int getCommentCount(String recipe_id);
	// 글 상세보기 댓글 보기
	List<RecipeCommentDto> commentDetailView(String recipe_id);
	// 글 상세보기 댓글 작성
	void writeComment(RecipeCommentDto dto);
}
