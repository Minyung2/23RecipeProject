package recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.dao.UsersDao;
import recipe.dto.SessionDto;
import recipe.dto.UsersDto;

@WebServlet("/project/loginCheck.do")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("../RecipeProject/Login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id=req.getParameter("user_id");
		String user_pw=req.getParameter("user_pw");
		UsersDao dao = new UsersDao();
		SessionDto user = new SessionDto();
		
		String check = dao.loginProcess(user_id, user_pw);
		if(!check.equals("")) {
			user=dao.sessionLogin(user_id);
			session.setAttribute("user",user);
			session.setMaxInactiveInterval(1800);

			resp.sendRedirect("../project/recipeList.do");
		}
		else {
			resp.sendRedirect("../RecipeProject/Login.jsp");
		}
	}
	
	
}
