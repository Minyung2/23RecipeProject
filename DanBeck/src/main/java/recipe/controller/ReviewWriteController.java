package recipe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.dao.RecipeReviewDao;
import recipe.dao.ReviewImgDao;
import recipe.dto.RecipeReviewDto;
import recipe.dto.ReviewImgDto;

@MultipartConfig(maxFileSize = 1024 * 1024 * 50)

@WebServlet("/project/writeReview.do")
public class ReviewWriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recipe_id=req.getParameter("recipe_id");
		req.setAttribute("recipe_id", recipe_id);
		req.getRequestDispatcher("/RecipeProject/ReviewWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String recipe_id = req.getParameter("recipe_id");
		System.out.println(recipe_id);
		String user_idx = req.getParameter("user_idx");
		String review_content = req.getParameter("review_content");
		int review_rating = Integer.parseInt(req.getParameter("rate"));
		RecipeReviewDto dto = new RecipeReviewDto();
		RecipeReviewDao dao = new RecipeReviewDao();
		dto.setRecipe_id(recipe_id);
		dto.setUser_idx(user_idx);
		dto.setReview_content(review_content);
		dto.setReview_rating(review_rating);
		dao.insertReview(dto);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			System.out.println("쓰레드 에러");
			e.printStackTrace();
		}
		String review_id = dao.getLastReviewId();
		
		ReviewImgDao idao = new ReviewImgDao();
		java.util.Collection<javax.servlet.http.Part> parts = req.getParts();
		int imageIndex=1;
		for (javax.servlet.http.Part file : parts) {
			if (!file.getName().equals("fileUpload"))
				continue;
				ReviewImgDto idto = new ReviewImgDto();
				String originName = file.getSubmittedFileName();
				String ext = originName.substring(originName.lastIndexOf("."));
				String newFileName = recipe_id+"_"+imageIndex+"rvImg"+ext;
				InputStream  fis = file.getInputStream();
				String realPath = req.getServletContext().getRealPath("/Storage");
				System.out.println(realPath);
				String filePath = realPath + File.separator + newFileName;
				FileOutputStream fos = new FileOutputStream(filePath);
				idto.setReview_id(review_id);
				idto.setImg_image_url(newFileName);
				idao.insertReviewImage(idto);
				imageIndex++;
			byte[] buf = new byte[1024];
			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fis.close();
			fos.close();
		}
		resp.sendRedirect("../project/recipeview.do?recipe_id="+recipe_id);

	}
}
