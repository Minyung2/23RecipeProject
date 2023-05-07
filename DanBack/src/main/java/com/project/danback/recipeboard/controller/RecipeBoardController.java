package com.project.danback.recipeboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.danback.utility.IngredientDtoWrapper;
import com.project.danback.utility.StepDtoWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RecipeBoardController {
    @Autowired
    RecipeBoardService dao;

    @Autowired
    HttpSession session;


    private Logger log1 = org.slf4j.LoggerFactory.getLogger(getClass());

    // 리스트 불러오기
    @RequestMapping("/recipeList")
    public String getRecipeList(@RequestParam Map<String, Object> param, Model model) {
        int currentPage;
        List<RecipeBoardDto> list;
        List<RecipeCategoryTypeDto> cate1List = dao.cate1List();
        List<RecipeCategoryTypeDto> cate2List = dao.cate2List();
        int totalCount;
        int pageSize = 10;
        String page = (String) param.get("page");
        if (page == null || page.trim().length() == 0)
            currentPage = 1;
        else
            currentPage = Integer.parseInt(page);
        PageDto pageDto;
        String cate1 = (String) param.get("cate1");
        String cate2 = (String) param.get("cate2");
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

    // 게시판 글쓰기 양식
    @GetMapping(value = "/recipeWrite")
    public String recipeWrite(Model model) {
        List<RecipeCategoryTypeDto> cate1List = dao.cate1List();
        List<RecipeCategoryTypeDto> cate2List = dao.cate2List();
        model.addAttribute("cate1", cate1List);
        model.addAttribute("cate2", cate2List);
        return "RecipeBoard/recipeWrite";
    }

    // 게시판 글쓰기 요청
    @Value("${part4.upload.path}")
    private String uploadPath;

    @PostMapping(value = "/recipeSave")
    public String recipeSave(
            RecipeBoardDto recipeBoardDto
            , @ModelAttribute("ingredientDtoWrapper") IngredientDtoWrapper ingredientDtoWrapper
            , @ModelAttribute("stepDtoWrapper") StepDtoWrapper stepDtoWrapper
            , @RequestPart("mainPhotoUpload") MultipartFile mf1
            , @RequestPart("fileUpload[]") MultipartFile[] mf2, Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UsersDto user = (UsersDto) authentication.getPrincipal();
//		String user_idx = user.getUser_idx();
        String user_idx = "1";
        recipeBoardDto.setUser_idx(user_idx);
        LocalDateTime now = LocalDateTime.now();

        List<RecipeIngredientDto> recipeIngredientList = ingredientDtoWrapper.getRecipeIngredientList();
        List<RecipeStepDto> recipeStepList =stepDtoWrapper.getRecipeStepList();

        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss"));
        boolean isEmpty = true;

        if (!mf1.isEmpty() && mf2 != null && mf2.length > 0) {
            for (MultipartFile file : mf2) {
                if (!file.isEmpty() || file.getContentType().startsWith("image") || mf1.getContentType().startsWith("image")) {
                    isEmpty = false;
                    break;
                }
            }
        }
        if (isEmpty) {
            return "RecipeBoard/recipeWrite";
        } else {
            try {
                String fileName = mf1.getOriginalFilename();
                String mainExt = fileName.substring(fileName.lastIndexOf("."));
                String mainFileName = formattedDateTime + "_mainPhoto" + mainExt;
                recipeBoardDto.setRecipe_image_url(mainFileName);
                dao.save(recipeBoardDto);
                String recipe_id = dao.getLastRecipeId();
                Path path = Paths.get(uploadPath + File.separator + user_idx + File.separator + recipe_id);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                mf1.transferTo(path.resolve(mainFileName));
                File mainPhoto = new File(uploadPath + File.separator + user_idx + File.separator + recipe_id + File.separator + mainFileName);

                for (RecipeStepDto temp : recipeStepList) {
                    int index = 0;
                    now = LocalDateTime.now();
                    formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss"));
                    String tempFile = mf2[index].getOriginalFilename();
                    String stepExt = tempFile.substring(tempFile.lastIndexOf("."));
                    String stepFileName = formattedDateTime + "_stepPhoto_" + index + stepExt;
                    RecipeStepDto recipeStepDto = new RecipeStepDto();
                    temp.setStep_image_url(stepFileName);
                    temp.setRecipe_id(recipe_id);
                    dao.stepSave(temp);
                    mf2[index].transferTo(path.resolve(stepFileName));
                    File stepUpload = new File(uploadPath + File.separator + user_idx + File.separator + recipe_id + File.separator + stepFileName);
                }

                for (RecipeIngredientDto ingredient : recipeIngredientList) {
                    ingredient.setRecipe_id(recipe_id);
                    dao.ingredientSave(ingredient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/recipeList";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sw.toString());
    }
    // 레피시 글 상세보기
    @RequestMapping("/recipeDetail")
    public String getRecipeDetailView(String recipe_id, Model model) {
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
        List<RecipeCommentDto> commentList = dao.commentDetailView(recipe_id);


        model.addAttribute("recipeDto", recipeDto);
        model.addAttribute("stepList", stepList);
        model.addAttribute("ingreList", ingreList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("reviewImgList", reviewImgList);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("commentList", commentList);
        return "RecipeBoard/recipeDetail";
    }


}
