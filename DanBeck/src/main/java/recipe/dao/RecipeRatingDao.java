package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeRatingDto;

public class RecipeRatingDao extends DBConnectpool {
	public RecipeRatingDao() {
		super();
	}
	
	public int getRatingCount(String recipe_id) {
		int count=0;
		String sql= "select count(*) from rating where recipe_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 후기 Count 조회중 DB 오류");
			e.printStackTrace();
		}
		return count;
	}
	
	public List<RecipeRatingDto> detailView(String recipe_id){
		List<RecipeRatingDto> list = new ArrayList<>();
		String sql= "select r.*, u.user_nickname from rating r, users u "
				+ "where u.user_idx=r.user_idx and recipe_id=? order by rating_id";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				RecipeRatingDto dto = new RecipeRatingDto();
				dto.setRating_id(rs.getString("rating_id"));
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setUser_idx(rs.getString("user_idx"));
				dto.setRating(rs.getString("rating"));
				dto.setRating_content(rs.getString("rating_content"));
				dto.setRating_date(rs.getTimestamp("rating_date"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("후기 조회중 DB 오류");
			e.printStackTrace();
		}
		
		return list;
	}
}
