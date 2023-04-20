package recipe.dto;

public class RecipeIngredientDto {
	private String recipe_id;
	private String ingredient_amount;
	private String ingredient_name;
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getIngredient_amount() {
		return ingredient_amount;
	}
	public void setIngredient_amount(String ingredient_amount) {
		this.ingredient_amount = ingredient_amount;
	}
	public String getIngredient_name() {
		return ingredient_name;
	}
	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
	}

	
}
