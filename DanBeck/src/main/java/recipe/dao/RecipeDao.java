package recipe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBpackage.DBConnectpool;
import recipe.dto.PageDto;
import recipe.dto.RecipeDto;

public class RecipeDao extends DBConnectpool {
	public RecipeDao() {
		super();
	}

	public int getCount(Map<String,Object> map) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM recipe r";
	    if (map.get("findText") != null && map.get("field").equals("T")) {
	        sql += " INNER JOIN recipe_category_kind rck ON rck.category_kind_idx = r.cate1 AND rck.category_kind_type ='방법별' AND rck.category_kind_name LIKE concat('%', ?, '%')";
	    } else if(map.get("findText") != null && map.get("field").equals("I")) {
	        sql += " INNER JOIN recipe_category_kind rck ON rck.category_kind_idx = r.cate1 AND rck.category_kind_type ='재료별' AND rck.category_kind_name LIKE concat('%', ?, '%')";
	    }
	    try {
	        psmt = con.prepareStatement(sql);
	        if (map.get("findText") != null) {
	            psmt.setString(1, map.get("findText").toString());
	        }
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


	public List<RecipeDto> getList(PageDto map) {
	    List<RecipeDto> list = new ArrayList<>();
	    String sql = "";
	    try {
	        sql = "SELECT r.*, u.user_nickname, rck.category_kind_name FROM recipe r LEFT JOIN users u ON u.user_idx = r.user_idx ";
	        if (map.getFindText() != null && map.getField().equals("T")) {
	            sql += "LEFT JOIN recipe_category_kind rck1 ON rck1.category_kind_idx = r.cate1 AND rck1.category_kind_type ='방법별' AND rck1.category_kind_name LIKE concat('%"+map.getFindText()+"%') ";
	        } else if (map.getFindText() != null && map.getField().equals("I")) {
	            sql += "LEFT JOIN recipe_category_kind rck2 ON rck2.category_kind_idx = r.cate1 AND rck2.category_kind_type ='재료별' AND rck2.category_kind_name LIKE concat('%"+map.getFindText()+"%') ";
	        }
	        sql += "LEFT JOIN recipe_category_kind rck ON rck.category_kind_idx = r.cate1 ";
	        sql += "ORDER BY recipe_id DESC LIMIT ?, ?";

	        System.out.println(sql);
	        psmt = con.prepareStatement(sql);
	        psmt.setInt(1, map.getStartNo());
	        psmt.setInt(2, map.getPageSize());
	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            RecipeDto dto = new RecipeDto();
	            dto.setRecipe_id(rs.getString("recipe_id"));
	            dto.setUser_idx(rs.getString("user_idx"));
	            dto.setRecipe_name(rs.getString("recipe_name"));
	            dto.setRecipe_desc(rs.getString("recipe_desc"));
	            dto.setRecipe_people(rs.getString("recipe_people"));
	            dto.setRecipe_time(rs.getString("recipe_time"));
	            dto.setRecipe_difficulty(rs.getString("recipe_difficulty"));
	            dto.setRecipe_image_url(rs.getString("recipe_image_url"));
	            dto.setUser_nickname(rs.getString("user_nickname"));
	            dto.setCate1(rs.getString("cate1"));
	            dto.setCate2(rs.getString("cate2"));
	            dto.setCategory_kind_name(rs.getString("category_kind_name"));
	            list.add(dto);
	        }
	    } catch (SQLException e) {
	        System.out.println("RecipeDao getRecipeList Error : " + e.getMessage());
	    } finally {
	        close();
	    }

	    return list;
	}

	public RecipeDto detailView(String recipe_id) {
		RecipeDto dto = new RecipeDto();
		String sql = "select * from recipe where recipe_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setRecipe_name(rs.getString("recipe_name"));
				dto.setRecipe_desc(rs.getString("recipe_desc"));
				dto.setRecipe_people(rs.getString("recipe_people"));
				dto.setRecipe_time(rs.getString("recipe_time"));
				dto.setRecipe_time(rs.getString("recipe_time"));
				dto.setRecipe_image_url(rs.getString("recipe_image_url"));
			}
		} catch (Exception e) {
			System.out.println("상세보기 불러오기 중 DB 오류");
			e.printStackTrace();
		}
		return dto;
	}

	public List<String> suggestionKeyword(String recipe_name) {
		String sql = "select recipe_name from recipe where recipe_name like concat('%" + recipe_name + "%')";
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
		String sql = "insert into recipe (user_idx,recipe_name,recipe_desc,recipe_people,recipe_time,recipe_difficulty,recipe_image_url,cate1,cate2) values(?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getUser_idx());
			psmt.setString(2, dto.getRecipe_name());
			psmt.setString(3, dto.getRecipe_desc());
			psmt.setString(4, dto.getRecipe_people());
			psmt.setString(5, dto.getRecipe_time());
			psmt.setString(6, dto.getRecipe_difficulty());
			psmt.setString(7, dto.getRecipe_image_url());
			psmt.setString(8, dto.getCate1());
			psmt.setString(9, dto.getCate2());

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		return result;
	}

	public String getLastRecipeId() {
		String lastIndex = "";
		String sql = "SELECT recipe_id FROM recipe WHERE recipe_id = (SELECT MAX(recipe_id) FROM recipe)";
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
