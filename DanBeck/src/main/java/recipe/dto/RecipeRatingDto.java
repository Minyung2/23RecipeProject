package recipe.dto;


import java.sql.Timestamp;

public class RecipeRatingDto {
	private String rating_id;
	private String recipe_id;
	private String user_idx;
	private String user_nickname;
	private String rating;
	private String rating_content;
	private Timestamp rating_date;
	
	
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getRating_id() {
		return rating_id;
	}
	public void setRating_id(String rating_id) {
		this.rating_id = rating_id;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRating_content() {
		return rating_content;
	}
	public void setRating_content(String rating_content) {
		this.rating_content = rating_content;
	}
	public Timestamp getRating_date() {
		return rating_date;
	}
	public void setRating_date(Timestamp rating_date) {
		this.rating_date = rating_date;
	}

	
	
}
