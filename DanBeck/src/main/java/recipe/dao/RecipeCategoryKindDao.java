package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeCategoryKindDto;

public class RecipeCategoryKindDao extends DBConnectpool{
	public RecipeCategoryKindDao() {
		super();
	}
	
	public List<RecipeCategoryKindDto> cateList(String category_idx){
		List<RecipeCategoryKindDto> cate1List = new ArrayList<>();
		String sql = "select * from recipe_category_kind where category_kind_type = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, category_idx);
			rs = psmt.executeQuery();
			while(rs.next()) {
				RecipeCategoryKindDto dto = new RecipeCategoryKindDto();
				dto.setCategory_kind_idx(rs.getString("category_kind_idx"));
				dto.setCategory_kind_name(rs.getString("category_kind_name"));
				dto.setCategory_idx(rs.getString("category_kind_type"));
				cate1List.add(dto);
			}
		}catch(Exception e) {
			System.out.println("카테고리1 리스트 조회 에러");
			e.printStackTrace();
		}
		return cate1List;
	}
	
	
}
