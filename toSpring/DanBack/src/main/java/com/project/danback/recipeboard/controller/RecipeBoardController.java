package com.project.danback.recipeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.danback.recipeboard.model.PageDto;
import com.project.danback.recipeboard.model.RecipeBoardDto;
import com.project.danback.recipeboard.model.RecipeCategoryTypeDto;
import com.project.danback.recipeboard.model.RecipeCommentDto;
import com.project.danback.recipeboard.model.RecipeIngredientDto;
import com.project.danback.recipeboard.model.RecipeReviewDto;
import com.project.danback.recipeboard.model.RecipeStepDto;
import com.project.danback.recipeboard.model.ReviewImgDto;
import com.project.danback.recipeboard.service.RecipeBoardService;
import com.project.danback.user.dto.UsersDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class RecipeBoardController {
	@Autowired
	RecipeBoardService dao;
	@Autowired
	HttpSession session;
	
	
	private Logger log1 = org.slf4j.LoggerFactory.getLogger(getClass());
	

	@RequestMapping("/recipeList")
	public String getRecipeList(@RequestParam Map<String, Object> param, Model model) {
		int currentPage;
		List<RecipeBoardDto> list;
		List<RecipeCategoryTypeDto> cate1List=dao.cate1List();
		List<RecipeCategoryTypeDto> cate2List=dao.cate2List();
		int totalCount;
		int pageSize = 10;
		String page = (String) param.get("page");
		if (page == null || page.trim().length() == 0)
			currentPage = 1;
		else
			currentPage = Integer.parseInt(page);
		PageDto pageDto;
		String cate1 = (String) param.get("cate1");
		String cate2= (String) param.get("cate2");
		totalCount = dao.getCount(param);
		pageDto = new PageDto(currentPage, totalCount, pageSize, cate1, cate2);
		list = dao.getList(pageDto);
		Map<String, Object> map = new HashMap<>();
		map.put("cate1", cate1);
		map.put("cate2", cate2);
		map.put("list", list);
		map.put("page", pageDto);
		map.put("cate1List", cate1List);
		map.put("cate2List", cate2List);
		model.addAllAttributes(map);
		log1.info("mybatis");
		return "RecipeBoard/recipeList";
	}
	
	@RequestMapping(value="/recipeWrite" , method = RequestMethod.GET)
	public String getRecipeList() {
		return "RecipeBoard/recipeWrite";
	}
	
	@RequestMapping("/recipeDetail")
	public String getRecipeDetailView(String recipe_id,Model model) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    Object principal = authentication.getPrincipal();

		    if (principal instanceof UsersDto) {
		        UsersDto user = (UsersDto) principal;
		        model.addAttribute("currentUser", user);
		    }
		RecipeBoardDto recipeDto = dao.detailView(recipe_id);
		List<RecipeStepDto> stepList = dao.stepDetailView(recipe_id);
		List<RecipeIngredientDto> ingreList = dao.ingreDetailView(recipe_id);
		List<RecipeReviewDto> reviewList = dao.reviewDetailView(recipe_id);
		int reviewCount = dao.getReviewCount(recipe_id);
		List<ReviewImgDto> reviewImgList = dao.reviewImgDetailView(recipe_id);
		int commentCount = dao.getCommentCount(recipe_id);
		List<RecipeCommentDto> commentList= dao.commentDetailView(recipe_id);
		
		
		model.addAttribute("recipeDto",recipeDto);
		model.addAttribute("stepList",stepList);
		model.addAttribute("ingreList",ingreList);
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("reviewCount",reviewCount);
		model.addAttribute("reviewImgList",reviewImgList);
		model.addAttribute("commentCount",commentCount);
		model.addAttribute("commentList",commentList);
		return "RecipeBoard/recipeDetail";
	}
	
	@PostMapping("commentWrite")
	@ResponseBody 
	public ResponseEntity<Map<String, Object>> commentWrite(@RequestBody RecipeCommentDto dto ,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UsersDto user = (UsersDto) authentication.getPrincipal();
	    String user_idx = user.getUser_idx();
	    dto.setUser_idx(user_idx);
		dao.writeComment(dto);
		int commentCount = dao.getCommentCount(dto.getRecipe_id());
		List<RecipeCommentDto> commentList= dao.commentDetailView(dto.getRecipe_id());
		
		Map<String, Object> response = new HashMap<>();
	    response.put("commentList", commentList);
	    response.put("commentCount", commentCount);
	    
	    return ResponseEntity.ok(response);
	}
}
