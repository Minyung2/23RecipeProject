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

@WebServlet("/project/commentWrite.do")
public class CommentWriteController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipe_id = req.getParameter("recipe_id");
        String user_idx = req.getParameter("user_idx");
        String comment_content= req.getParameter("comment_content");
        RecipeCommentDto dto = new RecipeCommentDto();
        dto.setRecipe_id(recipe_id);
        dto.setUser_idx(user_idx);
        dto.setComment_content(comment_content);
        
        RecipeCommentDao dao = new RecipeCommentDao();
        dao.writeComment(dto);
        
        List<RecipeCommentDto> commentList = dao.detailView(recipe_id);
        
        Gson gson = new Gson();
        String json = gson.toJson(commentList);
     
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}
