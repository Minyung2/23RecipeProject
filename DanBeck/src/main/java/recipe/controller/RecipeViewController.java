package recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dao.RecipeCommentDao;
import recipe.dao.RecipeDao;
import recipe.dao.RecipeIngredientDao;
import recipe.dao.RecipeReviewDao;
import recipe.dao.RecipeStepDao;
import recipe.dao.ReviewImgDao;
import recipe.dto.RecipeCommentDto;
import recipe.dto.RecipeDto;
import recipe.dto.RecipeIngredientDto;
import recipe.dto.RecipeReviewDto;
import recipe.dto.RecipeStepDto;
import recipe.dto.ReviewImgDto;

@WebServlet("/project/recipeview.do")
public class RecipeViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recipe_id = req.getParameter("recipe_id");
		RecipeDao recipeDao = new RecipeDao();
		RecipeIngredientDao recipeIngredientDao = new RecipeIngredientDao();
		RecipeStepDao stepDao = new RecipeStepDao();
		RecipeReviewDao ReviewDao = new RecipeReviewDao();
		RecipeCommentDao commentDao = new RecipeCommentDao();
		ReviewImgDao ReviewImageDao = new ReviewImgDao();
		RecipeDto recipeDto = recipeDao.detailView(recipe_id);
		List<RecipeIngredientDto> ingreList = recipeIngredientDao.detailView(recipe_id); 
		List<RecipeStepDto> stepList = stepDao.detailView(recipe_id);
		List<RecipeReviewDto> ReviewList = ReviewDao.detailView(recipe_id);
		int ReviewCount = ReviewDao.getreviewCount(recipe_id);
		List<RecipeCommentDto> commentList = commentDao.detailView(recipe_id);
		int commentCount = commentDao.getCommentCount(recipe_id);
		
		List<ReviewImgDto> reviewImgList = new ArrayList<>(); 
		for (RecipeReviewDto review : ReviewList) {
		    List<ReviewImgDto> images = ReviewImageDao.getImgList(recipe_id, review.getReview_id());
		    reviewImgList.addAll(images); 
		}
		
		req.setAttribute("recipeDto", recipeDto);
		req.setAttribute("ingreList", ingreList);
		req.setAttribute("stepList", stepList);
		req.setAttribute("ReviewList", ReviewList);
		req.setAttribute("ReviewCount", ReviewCount);
		req.setAttribute("commentList", commentList);
		req.setAttribute("commentCount", commentCount);
		req.setAttribute("reviewImgList", reviewImgList);
		recipeDao.close();
		recipeIngredientDao.close();
		stepDao.close();
		ReviewDao.close();
		commentDao.close();
		ReviewImageDao.close();
		req.getRequestDispatcher("../RecipeProject/RecipeView.jsp").forward(req, resp);
	}
}
