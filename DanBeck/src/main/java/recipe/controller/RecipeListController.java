package recipe.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dao.RecipeDao;
import recipe.dto.PageDto;
import recipe.dto.RecipeDto;

@WebServlet("/project/recipeList.do")
public class RecipeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDao dao = new RecipeDao();
		
		int pageNo;
		if(request.getParameter("page")==null) pageNo=1;
		else pageNo = Integer.parseInt(request.getParameter("page"));   //page=1,2,3,4,.....

		int pageSize =10;	
		PageDto pageDto = new PageDto(pageNo,dao.getCount(),pageSize);  //페이지처리에 필요한객체(값) 생성
		Map<String,Integer> map = new HashMap<>();
		map.put("pageSize",pageSize);
		map.put("startNo",pageDto.getStartNo());
		List<RecipeDto> list = dao.getList(map);
		
		request.setAttribute("today", LocalDate.now());
		request.setAttribute("pageDto", pageDto);     //페이지처리에 필요한 값들
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("../RecipeProject/RecipeList.jsp").forward(request, response);
	}
}
