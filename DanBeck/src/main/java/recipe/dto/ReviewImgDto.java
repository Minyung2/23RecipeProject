package recipe.dto;

public class ReviewImgDto {
	private String img_id;
	private String review_id;
	private String img_image_url;
	private String recipe_id;
	public String getImg_id() {
		return img_id;
	}
	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getImg_image_url() {
		return img_image_url;
	}
	public void setImg_image_url(String img_image_url) {
		this.img_image_url = img_image_url;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	@Override
	public String toString() {
		return "ReviewImgDto [img_id=" + img_id + ", img_image_url=" + img_image_url + ", recipe_id=" + recipe_id
				+ ", review_id=" + review_id + "]";
	}
	
	
	
}
