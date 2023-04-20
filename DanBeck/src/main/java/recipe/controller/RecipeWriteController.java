package recipe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.dao.IngredientDao;
import recipe.dao.RecipeDao;
import recipe.dao.RecipeIngredientDao;
import recipe.dao.RecipeStepDao;
import recipe.dto.RecipeDto;
import recipe.dto.RecipeIngredientDto;
import recipe.dto.RecipeStepDto;

@MultipartConfig(maxFileSize = 1024 * 1024 * 50)

@WebServlet("/project/recipeWrite.do")
public class RecipeWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/RecipeProject/RecipeInsert.jsp").forward(req, resp);
		}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_idx = "1";
		String recipe_name = req.getParameter("recipe_name");
		String recipe_desc = req.getParameter("recipe_desc");
		String recipe_amount_portion = req.getParameter("recipe_amount");
		String recipe_cooking_time = req.getParameter("recipe_cooking_time");
		String recipe_difficulty = req.getParameter("recipe_difficulty");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setUser_idx(user_idx);
		recipeDto.setRecipe_name(recipe_name);
		recipeDto.setRecipe_desc(recipe_desc);
		recipeDto.setRecipe_people(recipe_amount_portion);
		recipeDto.setRecipe_time(recipe_cooking_time);
		
		String fileName = req.getPart("mainPhotoUpload").getSubmittedFileName();
		String mainext = fileName.substring(fileName.lastIndexOf("."));
		String mainFileName = user_idx+"_"+recipe_name+"_mainPhoto"+mainext;
		String path = req.getServletContext().getRealPath("/Storage");
//		mainFileName = path+"\\" + mainFileName;
		InputStream is = req.getPart("mainPhotoUpload").getInputStream();
		FileOutputStream os = new FileOutputStream(path+"\\"+mainFileName);
		System.out.println(path);
		byte[] buffer = new byte[1024];
		while (is.read(buffer) > 0) {
			os.write(buffer);
		}
		is.close();
		os.close();
		
		recipeDto.setRecipe_image_url(mainFileName);
		recipeDto.setRecipe_difficulty(recipe_difficulty);
		RecipeDao recipeDao = new RecipeDao();
		recipeDao.insertRecipe(recipeDto);
		RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
		IngredientDao ingredientDao = new IngredientDao();
		
		// DB 입력후 recipe_id 받아오기 위해 쓰레드 sleep 처리
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			System.out.println("Thread error");
		}

		String recipe_id = recipeDao.getLastRecipeId();
		RecipeIngredientDao recipeIngredientDao = new RecipeIngredientDao();
		String[] recipe_ingre_name = req.getParameterValues("ingredient_name[]");
		String[] ingredient_amount = req.getParameterValues("ingredient_amount[]");
		for (int i = 0; i < recipe_ingre_name.length; i++) {
			if (!recipe_ingre_name[i].equals("")) {
				recipeIngredientDto.setRecipe_id(recipe_id);
				recipeIngredientDto.setIngredient_amount(ingredient_amount[i]);
				recipeIngredientDto.setIngredient_name(recipe_ingre_name[i]);
				recipeIngredientDao.insertIngredient(recipeIngredientDto);
			}
		}
		
		
		
		RecipeStepDto stepDto = new RecipeStepDto();
		RecipeStepDao stepDao = new RecipeStepDao();
		String[] stepDesc = req.getParameterValues("step_text[]");
		java.util.Collection<javax.servlet.http.Part> parts = req.getParts();
		int imageIndex=1;
		int stepIndex=0;
		for (javax.servlet.http.Part file : parts) {
			if (!file.getName().equals("fileUpload"))
				continue;
				String originName = file.getSubmittedFileName();
				String ext = originName.substring(originName.lastIndexOf("."));
				String newFileName = recipe_id+"_"+imageIndex+ext;
				
				InputStream  fis = file.getInputStream();
				String realPath = req.getServletContext().getRealPath("/Storage");
				String filePath = realPath + File.separator + newFileName;
				FileOutputStream fos = new FileOutputStream(filePath);
				stepDto.setRecipe_id(recipe_id);
				stepDto.setStep_desc(stepDesc[stepIndex]);
				stepDto.setStep_image_url(newFileName);
				stepDao.insertSteps(stepDto);
				stepIndex++;
				imageIndex++;
			byte[] buf = new byte[1024];
			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fis.close();
			fos.close();
		}
		resp.sendRedirect("../project/recipeList.do");
	}
}
