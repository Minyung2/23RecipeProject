package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeCommentDto;

public class RecipeCommentDao extends DBConnectpool {
	public RecipeCommentDao() {
		super();
	}

	public int getCommentCount(String recipe_id) {
		int count=0;
		String sql= "select count(*) from recipe_comment where recipe_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 댓글 Count 조회 중 DB 오류");
			e.printStackTrace();
		}
		return count;
	}
	
	public List<RecipeCommentDto> detailView(String recipe_id) {
		List<RecipeCommentDto> list = new ArrayList<>();
		String sql="select rc.*,u.user_nickname  from recipe_comment rc,users u  where u.user_idx =rc.user_idx and recipe_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				RecipeCommentDto dto = new RecipeCommentDto();
				dto.setComment_id(rs.getString("comment_id"));
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setUser_idx(rs.getString("user_idx"));
				dto.setComment_content(rs.getString("comment_content"));
				dto.setComment_date(rs.getDate("comment_date"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 댓글 리스트 조회중 DB 에러");
			e.printStackTrace();
		}
		return list;
	}
	
	public int writeComment(RecipeCommentDto dto) {
		int result=0;
		String sql="insert into recipe_comment(recipe_id,user_idx,comment_content) values(?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1,dto.getRecipe_id());
			psmt.setString(2,dto.getUser_idx());
			psmt.setString(3,dto.getComment_content());
			result=psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 INSERT 중 DB 에러");
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteComment(String comment_id) {
		int result=0;
		String sql = "DELETE FROM recipes.recipe_comment WHERE comment_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, comment_id);
			result = psmt.executeUpdate();
			System.out.println("댓글 삭제 완료");
		} catch (Exception e) {
			System.out.println("댓글 삭제 중 DB 에러");
			e.printStackTrace();
		}
		return result;
	}

}
