package recipe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.dao.IngredientDao;
import recipe.dao.RecipeCategoryKindDao;
import recipe.dao.RecipeDao;
import recipe.dao.RecipeIngredientDao;
import recipe.dao.RecipeStepDao;
import recipe.dto.RecipeCategoryKindDto;
import recipe.dto.RecipeDto;
import recipe.dto.RecipeIngredientDto;
import recipe.dto.RecipeStepDto;
import recipe.dto.SessionDto;
import recipe.utility.Alert;

@MultipartConfig(maxFileSize = 1024 * 1024 * 50)

@WebServlet("/project/recipeWrite.do")
public class RecipeWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RecipeCategoryKindDao rckDao= new RecipeCategoryKindDao();
		Map<String, Object> map = new HashMap<>();
		List<RecipeCategoryKindDto> cate1 = new ArrayList<>();
		List<RecipeCategoryKindDto> cate2 = new ArrayList<>();
		cate1 = rckDao.cateList("방법별");
		cate2 = rckDao.cateList("재료별");
		rckDao.close();
		map.put("cate1", cate1);
		map.put("cate2", cate2);
		
		req.setAttribute("map", map);
		req.getRequestDispatcher("/RecipeProject/RecipeInsert.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionDto user = (SessionDto) session.getAttribute("user");
		String user_idx = user.getUser_idx();
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
		if(fileName.trim().length()==0||req.getPart("mainPhotoUpload") == null) {
			Alert.alertBack(resp, "파일 입력 필요");
		}
		String mainext = fileName.substring(fileName.lastIndexOf("."));
		String mainFileName = user_idx + "_" + recipe_name + "_mainPhoto" + mainext;
		String path = req.getServletContext().getRealPath("/Storage");
		System.out.println(path);
//		mainFileName = path+"\\" + mainFileName;
		InputStream is = req.getPart("mainPhotoUpload").getInputStream();
		FileOutputStream os = new FileOutputStream(path + "\\" + mainFileName);
		System.out.println(path);
		byte[] buffer = new byte[1024];
		while (is.read(buffer) > 0) {
			os.write(buffer);
		}
		is.close();
		os.close();

		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		recipeDto.setCate1(cate1);
		recipeDto.setCate2(cate2);
		
		
		recipeDto.setRecipe_image_url(mainFileName);
		recipeDto.setRecipe_difficulty(recipe_difficulty);
		RecipeDao recipeDao = new RecipeDao();
		recipeDao.insertRecipe(recipeDto);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			System.out.println("쓰레드 오류");
			e.printStackTrace();
		}
		
		RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
		IngredientDao ingredientDao = new IngredientDao();
		
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
		int imageIndex = 1;
		int stepIndex = 0;
		java.util.Collection<javax.servlet.http.Part> parts = req.getParts();
		if (parts == null || parts.isEmpty()) {
		    Alert.alertBack(resp, "요리 과정 사진을 입력하셔야 합니다.");
		} else {
			for (javax.servlet.http.Part file : parts) {
				if (!file.getName().equals("fileUpload"))
					continue;
				String originName = file.getSubmittedFileName();
				String ext = originName.substring(originName.lastIndexOf("."));
				String newFileName = recipe_id + "_" + imageIndex + ext;

				InputStream fis = file.getInputStream();
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
				resp.sendRedirect("../project/recipeList.do");
			}
		}

		ingredientDao.close();
		recipeIngredientDao.close();
		recipeDao.close();
		stepDao.close();
	}
}
