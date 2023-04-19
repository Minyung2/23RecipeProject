package recipe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeDto;

public class RecipeDao extends DBConnectpool {
	public RecipeDao() {
		super();
	}

	public int getCount() {
		int count = 0;
		String sql = "select count(*) from recipe_test";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("게시물 Count 조회중 에러");
			e.printStackTrace();
		}
		return count;
	}


	public List<RecipeDto> getList(Map<String, Integer> map) {
		List<RecipeDto> list = new ArrayList<>();
		String sql = "SELECT rt.*,u.user_nickname FROM recipe_test rt,users u where u.user_idx=rt.user_idx ORDER BY recipe_id DESC LIMIT ?, ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, map.get("startNo"));
			psmt.setInt(2, map.get("pageSize"));
			rs = psmt.executeQuery();
			while (rs.next()) {
				RecipeDto dto = new RecipeDto();
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setUser_idx(rs.getString("user_idx"));
				dto.setRecipe_name(rs.getString("recipe_name"));
				dto.setRecipe_desc(rs.getString("recipe_desc"));
				dto.setAmount_portion(rs.getString("amount_portion"));
				dto.setCooking_time(rs.getString("cooking_time"));
				dto.setDifficulty(rs.getString("difficulty"));
				dto.setImage_url(rs.getString("image_url"));
				dto.setUser_nickname(rs.getString("u.user_nickname"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("레시피 리스트 조회중 DB 에러");
			e.printStackTrace();
		}
		return list;
	}

	public RecipeDto detailView(String recipe_id) {
		RecipeDto dto = new RecipeDto();
		String sql = "select * from recipe_test where recipe_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setRecipe_name(rs.getString("recipe_name"));
				dto.setRecipe_desc(rs.getString("recipe_desc"));
				dto.setAmount_portion(rs.getString("amount_portion"));
				dto.setCooking_time(rs.getString("cooking_time"));
				dto.setDifficulty(rs.getString("difficulty"));
				dto.setImage_url(rs.getString("image_url"));
			}
		} catch (Exception e) {
			System.out.println("상세보기 불러오기 중 DB 오류");
			e.printStackTrace();
		}
		return dto;
	}

	public List<String> suggestionKeyword(String recipe_name) {
		String sql = "select recipe_name from recipe_test where recipe_name like concat('%" + recipe_name + "%')";
		List<String> list = new ArrayList<>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		return list;
	}

	public int insertRecipe(RecipeDto dto) {
		int result = 0;
		String sql = "insert into recipe_test (user_idx,recipe_name,recipe_desc,amount_portion,cooking_time,difficulty,image_url) values(?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getUser_idx());
			psmt.setString(2, dto.getRecipe_name());
			psmt.setString(3, dto.getRecipe_desc());
			psmt.setString(4, dto.getAmount_portion());
			psmt.setString(5, dto.getCooking_time());
			psmt.setString(6, dto.getDifficulty());
			psmt.setString(7, dto.getImage_url());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		return result;
	}

	public String getLastRecipeId() {
		String lastIndex = "";
		String sql = "SELECT recipe_id FROM recipe_test WHERE recipe_id = (SELECT MAX(recipe_id) FROM recipe_test)";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				lastIndex = rs.getString("recipe_id");
		} catch (Exception e) {
			System.out.println("게시글 마지막 index 조회중 DB 에러");
			e.printStackTrace();
		}
		return lastIndex;
	}
}
