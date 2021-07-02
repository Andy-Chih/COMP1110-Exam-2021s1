package comp1110.exam;

import java.util.*;

/**
 * This class represents a collection of recipes and ingredients.
 * In this collection, each recipe has:
 * - a unique quick reference (String);
 * - a name (String); and
 * - a category (String).
 * Multiple recipes may have the same category.
 * Each recipe also contains zero or more ingredients.
 * Each ingredient has a quick reference (String) and a name (String).
 */
public class Q3Recipes {
    /**
     * Add a new recipe to this collection. If a recipe already exists with
     * the given quickRef, do not modify this collection.
     *
     * @param quickRef    the quick reference ID for the recipe
     * @param name        the name of the recipe
     * @param category    the category of the recipe
     * @param ingredients the ingredients in the recipe
     * @return true if the recipe was added to this collection, or false if the
     * * recipe was not added (because it already exists)
     */
    public boolean addRecipe(String quickRef, String name, String category, Set<String> ingredients) {
        // FIXME complete this method
        return false;
    }

    /**
     * Delete the specified recipe from this collection.
     *
     * @param quickRef the quick reference of the recipe to be deleted
     * @return true if the specified recipe was found and deleted,
     * or false if the specified recipe was not found in this collection
     */
    public boolean deleteRecipe(String quickRef) {
        // FIXME complete this method
        return false;
    }

    /**
     * @return the total number of recipes in this collection
     */
    public int getRecipeCount() {
        // FIXME complete this method
        return 0;
    }

    /**
     * Gets the set of names of all recipes that contain the given ingredient.
     *
     * @param ingredient the name of the ingredient
     * @return the set of names of all recipes that include the given ingredient
     * (if no recipes include the given ingredient, this will be the empty set)
     */
    public Set<String> getRecipesContaining(String ingredient) {
        // FIXME complete this method
        return null;
    }

    /**
     * Gets the set of names of all recipes for the given category.
     *
     * @param category the name of the category to search for
     * @return the set of names of all recipes in the given category
     * (if there are no recipes for the category, this will be the empty set)
     */
    public Set<String> getRecipesForCategory(String category) {
        // FIXME complete this method
        return null;
    }

    /**
     * Gets the size of the largest set of recipes containing the same ingredient.
     * For example, if there are four recipes in this collection:
     * - "Choc67" (category: Dessert) contains "Sugar", "Butter"
     * - "Praw82" (category: Entree) contains "Seafood"
     * - "Toff67" (category: Dessert) contains "Fruit" and "Sugar"
     * - "Spag70" (category: Main) contains  "Vegetable", "Meat" and "Pasta"
     * then getMaxRecipesForIngredient() == 2 (for the ingredient "Sugar")
     *
     * @return the size of the largest set of recipes containing the same ingredient
     */
    public int getMaxRecipesForIngredient() {
        // FIXME complete this method
        return -1;
    }

    /**
     * Get the number of categories of recipes that contain the given ingredient.
     * For example, if the ingredient "Fe" is contained in the following recipes:
     * - "Spag70" (category: Main) contains  "Vegetable", "Meat" and "Pasta"
     * - "Frui66" (category: Dessert) contains "Fruit"
     * - "Toff67" (category: Dessert) contains "Fruit" and "Sugar"
     * - "Curr87" (catgory: Main) contains "Fruit", "Sugar" and "Chocolate"
     * <p>
     * then getNumCategoriesContaining("Fruit") == 2.
     *
     * @param ingredient the quick reference of the ingredient
     * @return the number of distinct categories of recipe that contain the ingredient
     */
    public int getNumCategoriesContaining(String ingredient) {
        // FIXME complete this method
        return -1;
    }

    /**
     * Get the number of ingredients occurring in recipes of more than one category.
     * For example, if there are four recipes in this collection:
     * - "Fudg67" (category: Dessert) contains "Sugar", "Butter"
     * - "Praw77" (category: Entree) contains "Seafood"
     * - "Toff67" (category: Dessert) contains "Fruit" and "Sugar"
     * - "Tuna76" (category: Main) contains "Seafood"
     * - "Duck81" (category: Entree) contains "Meat", "Fruit"
     * then getNumCrossCategoryIngredients() == 2 because "Seafood" is contained in
     * recipes in the "Entree" and "Main" categories and "Fruit" is contained
     * in recipes of the "Dessert" and "Entree".
     *
     * @return the number of ingredients which occur in recipes of more than one category
     */
    public int getNumCrossCategoryIngredients() {
        // FIXME complete this method
        return -1;
    }

    /**
     * Get the maximum number of recipes in any category.
     * For example, if there are four recipes in this collection:
     * - "Fudg67" (category: Dessert) contains "Sugar", "Butter"
     * - "Praw77" (category: Entree) contains "Seafood"
     * - "Toff67" (category: Dessert) contains "Fruit" and "Sugar"
     * - "Tuna76" (category: Main) contains "Seafood"
     * then getMaxRecipesInCategory() == 2 (for the category "Dessert")
     *
     * @return the largest number of recipes in any category
     */
    public int getMaxRecipesInCategory() {
        // you are not required to complete this method for Q3.1,
        // but it may help you to complete Q3.2
        return -1;
    }
}


