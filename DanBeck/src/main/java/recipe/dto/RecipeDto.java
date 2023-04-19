package recipe.dto;

public class RecipeDto {
	private String recipe_id;
	private String user_idx;
	private String recipe_name;
	private String user_nickname;
	private String recipe_desc;
	private String amount_portion;
	private String cooking_time; 
	private String difficulty;
	private String image_url;
	private int visitCount;
	

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getRecipe_id() {
		return recipe_id;
	}
	

	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRecipe_name() {
		return recipe_name;
	}
	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public String getRecipe_desc() {
		return recipe_desc;
	}
	public void setRecipe_desc(String recipe_desc) {
		this.recipe_desc = recipe_desc;
	}
	public String getAmount_portion() {
		return amount_portion;
	}
	public void setAmount_portion(String amount_portion) {
		this.amount_portion = amount_portion;
	}
	public String getCooking_time() {
		return cooking_time;
	}
	public void setCooking_time(String cooking_time) {
		this.cooking_time = cooking_time;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
	
}
