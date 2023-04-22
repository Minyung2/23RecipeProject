package recipe.dto;

public class RecipeDto {
	private String recipe_id;
	private String user_idx;
	private String recipe_name;
	private String user_nickname;
	private String recipe_desc;
	private String recipe_people;
	private String recipe_time; 
	private String recipe_difficulty;
	private String recipe_image_url;
	private int recipe_visitCount;
	private String cate1;
	private String cate2;
	private String category_kind_name;
	
	public String getCategory_kind_name() {
		return category_kind_name;
	}
	public void setCategory_kind_name(String category_kind_name) {
		this.category_kind_name = category_kind_name;
	}
	public String getCate1() {
		return cate1;
	}
	public void setCate1(String cate1) {
		this.cate1 = cate1;
	}
	public String getCate2() {
		return cate2;
	}
	public void setCate2(String cate2) {
		this.cate2 = cate2;
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
	public String getRecipe_name() {
		return recipe_name;
	}
	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getRecipe_desc() {
		return recipe_desc;
	}
	public void setRecipe_desc(String recipe_desc) {
		this.recipe_desc = recipe_desc;
	}
	public String getRecipe_people() {
		return recipe_people;
	}
	public void setRecipe_people(String recipe_people) {
		this.recipe_people = recipe_people;
	}
	public String getRecipe_time() {
		return recipe_time;
	}
	public void setRecipe_time(String recipe_time) {
		this.recipe_time = recipe_time;
	}
	public String getRecipe_difficulty() {
		return recipe_difficulty;
	}
	public void setRecipe_difficulty(String recipe_difficulty) {
		this.recipe_difficulty = recipe_difficulty;
	}
	public String getRecipe_image_url() {
		return recipe_image_url;
	}
	public void setRecipe_image_url(String recipe_image_url) {
		this.recipe_image_url = recipe_image_url;
	}
	public int getRecipe_visitCount() {
		return recipe_visitCount;
	}
	public void setRecipe_visitCount(int recipe_visitCount) {
		this.recipe_visitCount = recipe_visitCount;
	}
	

	

	
	
	
}
