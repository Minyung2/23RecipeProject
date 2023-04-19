package recipe.dao;

import DBpackage.DBConnectpool;

public class IngredientDao extends DBConnectpool{
	public IngredientDao() {
		super();
	}
	
	public int insertIngredient(String ingredient_name) {	
		int result=0;
		String sql="insert into ingredient(ingredient_name) values(?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1,ingredient_name);
			result=psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 INSERT 중 DB 에러");
			e.printStackTrace();
		}
		return result;
	}
}
