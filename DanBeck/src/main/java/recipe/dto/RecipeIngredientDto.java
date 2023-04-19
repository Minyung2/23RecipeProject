package recipe.dto;

public class RecipeIngredientDto {
	private String recipe_id;
	private String amount;
	private String ingredient_name;


	public String getIngredient_name() {
		return ingredient_name;
	}
	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	
}
