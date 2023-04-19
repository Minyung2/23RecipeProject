package recipe.dto;

public class RecipeStepDto {
	private String step_id;
	private String recipe_id;
	private String step_desc;
	private String step_image_url;
	
	public String getStep_id() {
		return step_id;
	}
	public void setStep_id(String step_id) {
		this.step_id = step_id;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getStep_desc() {
		return step_desc;
	}
	public void setStep_desc(String step_desc) {
		this.step_desc = step_desc;
	}
	public String getStep_image_url() {
		return step_image_url;
	}
	public void setStep_image_url(String step_image_url) {
		this.step_image_url = step_image_url;
	}
	
	
	
}
