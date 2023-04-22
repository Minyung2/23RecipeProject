package recipe.controller;

import java.io.IOException;
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
		int currentPage;
		RecipeDao dao = new RecipeDao();
		List<RecipeDto> list;
		int totalCount;
		int pageSize = 10;
		String page=request.getParameter("page");
		if(page==null || page.trim().length()==0) currentPage=1;
		else currentPage = Integer.parseInt(page);
		
		String findText = request.getParameter("findText");
		String field = request.getParameter("field");
		Map<String, Object> map = new HashMap<>();
		if(findText!=null) {
			map.put("field",field);
			map.put("findText",findText);
		}
		// 총 게시글 수를 구합니다.
		totalCount= dao.getCount(map);
		dao.close();
		
		System.out.println(totalCount);
		// 현재 페이지 정보를 바탕으로 페이징 정보를 계산합니다.
		PageDto pageDto = new PageDto(currentPage, totalCount, pageSize, field, findText);
		System.out.println(pageDto);
		// 해당 페이지에 해당하는 게시글 리스트를 DAO를 통해 조회합니다.
		list = dao.getList(pageDto);
		// 검색어와 검색 필드, 페이징 정보를 Attribute로 설정하고 JSP로 forward합니다.
		request.setAttribute("list", list);
		request.setAttribute("page", pageDto);
		request.getRequestDispatcher("../RecipeProject/RecipeList.jsp?page=?"+page+"&field="+field+"&findText="+findText).forward(request, response);
	}
}
