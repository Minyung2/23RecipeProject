package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.RecipeReviewDto;
import recipe.dto.ReviewImgDto;

public class RecipeReviewDao extends DBConnectpool {
	public RecipeReviewDao() {
		super();
	}
	
	public int getreviewCount(String recipe_id) {
		int count=0;
		String sql= "select count(*) from review where recipe_id=?";
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
	
	public List<RecipeReviewDto> detailView(String recipe_id){
		List<RecipeReviewDto> list = new ArrayList<>();
		String sql= "select r.*, u.user_nickname from review r, users u "
				+ "where u.user_idx=r.user_idx and recipe_id=? order by review_id";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				RecipeReviewDto dto = new RecipeReviewDto();
				dto.setReview_id(rs.getString("review_id"));
				dto.setRecipe_id(rs.getString("recipe_id"));
				dto.setUser_idx(rs.getString("user_idx"));
				dto.setReview_rating(rs.getInt("review_rating"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_date(rs.getTimestamp("review_date"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("후기 조회중 DB 오류");
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insertReview(RecipeReviewDto dto) {
		int res=0;
		String sql = "insert into review (recipe_id, user_idx, review_rating, review_content) values(?,?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getRecipe_id());
			psmt.setString(2, dto.getUser_idx());
			psmt.setInt(3, dto.getReview_rating());
			psmt.setString(4, dto.getReview_content());
			res = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("리뷰 입력중 DB 오류");
			e.printStackTrace();
		}
		return res;
	}
	
	public String getLastReviewId() {
		String lastIndex = "";
		String sql = "SELECT review_id FROM review WHERE review_id = (SELECT MAX(review_id) FROM review)";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				lastIndex = rs.getString("review_id");
		} catch (Exception e) {
			System.out.println("게시글 마지막 index 조회중 DB 에러");
			e.printStackTrace();
		}
		return lastIndex;
	}
	
}
