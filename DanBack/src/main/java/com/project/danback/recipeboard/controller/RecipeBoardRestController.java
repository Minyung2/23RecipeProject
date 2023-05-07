package com.project.danback.recipeboard.controller;

import com.project.danback.recipeboard.model.RecipeCommentDto;
import com.project.danback.recipeboard.service.RecipeBoardService;
import com.project.danback.user.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeBoardRestController {
    @Autowired
    RecipeBoardService dao;
    // 레시피 댓글 달기
    @PostMapping("commentWrite")
    public ResponseEntity<Map<String, Object>> commentWrite(@RequestBody RecipeCommentDto dto , Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDto user = (UsersDto) authentication.getPrincipal();
        String user_idx = user.getUser_idx();
        dto.setUser_idx(user_idx);
        dao.writeComment(dto);
        int commentCount = dao.getCommentCount(dto.getRecipe_id());
        List<RecipeCommentDto> commentList= dao.commentDetailView(dto.getRecipe_id());

        Map<String, Object> response = new HashMap<>();
        response.put("commentList", commentList);
        response.put("commentCount", commentCount);

        return ResponseEntity.ok(response);


    }
}
