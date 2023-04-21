package recipe.dao;

import java.util.ArrayList;
import java.util.List;

import DBpackage.DBConnectpool;
import recipe.dto.ReviewImgDto;

public class ReviewImgDao extends DBConnectpool{
	public ReviewImgDao() {
		super();
	}
	
	public int insertReviewImage(ReviewImgDto dto) {
		int res=0;
		String sql = "insert into review_img(review_id, img_image_url) values(?,?)";
		try {
			psmt= con.prepareStatement(sql);
			psmt.setString(1, dto.getReview_id());
			psmt.setString(2, dto.getImg_image_url());
			res = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("리뷰 이미지 데이터 저장중 DB 에러");
			e.printStackTrace();
		}	
		return res;
	}
	
	public List<ReviewImgDto> getImgList(String recipe_id,String review_id){
		List<ReviewImgDto> list = new ArrayList<>();
		String sql = "SELECT ri.img_image_url, r.review_id, r.recipe_id FROM review r JOIN review_img ri ON r.review_id = ri.review_id WHERE r.recipe_id = ? and ri.review_id = ?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, recipe_id);
			psmt.setString(2, review_id);
			rs= psmt.executeQuery();
			while(rs.next()) {
				ReviewImgDto dto = new ReviewImgDto();
				dto.setImg_image_url(rs.getString(1));
				dto.setReview_id(rs.getString(2));
				dto.setRecipe_id(rs.getString(3));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("이미지 리스트 DB 조회 실패");
			e.printStackTrace();
		}
		return list;
	}
}
