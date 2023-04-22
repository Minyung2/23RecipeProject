package recipe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import recipe.dao.RecipeCommentDao;
import recipe.dto.RecipeCommentDto;

@WebServlet("/project/commentDelete.do")
public class CommentDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recipe_id = req.getParameter("recipe_id");
		String comment_id = req.getParameter("comment_id");
		RecipeCommentDao dao = new RecipeCommentDao();
		dao.deleteComment(comment_id);
	
		List<RecipeCommentDto> commentList = dao.detailView(recipe_id);

		Gson gson = new Gson();
		String json = gson.toJson(commentList);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
		dao.close();
		
	}
}
