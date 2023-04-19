package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeIngredientDto;

public class RecipeIngredientDao extends DBConnectpool {
	public RecipeIngredientDao() {
		super();
	}
	
	public List<RecipeIngredientDto> detailView(String id) {
		String sql = "select * from recipe_ingredient where recipe_id=?";
		List<RecipeIngredientDto> list= new ArrayList<>();
		try {
			psmt= con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				RecipeIngredientDto dto = new RecipeIngredientDto();
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setIngredient_name(rs.getString("ingredient_name"));
				dto.setAmount(rs.getString("amount"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("재료 검색 중 DB 에러");
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void insertIngredient(RecipeIngredientDto dto) {
		String sql = "insert into recipe_ingredient(recipe_id,ingredient_name,amount) values(?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getRecipe_id());
			psmt.setString(2, dto.getIngredient_name());
			psmt.setString(3, dto.getAmount());
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("재료 DB 입력중 에러");
			e.printStackTrace();
		}
	}
}
