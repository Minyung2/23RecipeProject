package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeStepDto;

public class RecipeStepDao extends DBConnectpool {
	public RecipeStepDao() {
		super();
	}
	
	public List<RecipeStepDto> detailView(String recipe_id){
		List<RecipeStepDto> list = new ArrayList<>();
		String sql="select * from recipe_step where recipe_id=? order by step_id";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				RecipeStepDto dto = new RecipeStepDto();
				dto.setStep_id(rs.getString("step_id"));
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setStep_desc(rs.getString("step_desc"));
				dto.setStep_image_url(rs.getString("step_image_url"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("조리 과정 조회중 오류 발생");
		}
		return list;
	}
	
	public int insertSteps(RecipeStepDto dto) {
		int res =0 ;
		String sql ="insert into recipe_step(recipe_id,step_desc,step_image_url) values(?,?,?)";
		try {
			psmt= con.prepareStatement(sql);
			psmt.setString(1, dto.getRecipe_id());
			psmt.setString(2, dto.getStep_desc());
			psmt.setString(3, dto.getStep_image_url());
			res = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("조리 과정 DB 입력 중 에러");
			e.printStackTrace();
		}
		return res;
	}
	
}
