package comp1110.exam;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q3RecipesTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    String[] quickRef = new String[]{
            "Choc67",
            "Praw82",
            "Toff67",
            "Tuna88",
            "Duck66",
            "Spag70",
            "Frui66",
            "Curr87",
            "Appl67",
            "Gree77",
            "Zucc71"
    };

    String[] recipeNames = new String[]{
            "Chocolate Fudge",
            "Prawn Skewers",
            "Toffee Apple",
            "Tuna Mornay",
            "Duck Salad",
            "Spaghetti",
            "Fruit Salad",
            "Curry",
            "Apple Pie",
            "Green Tea",
            "Zucchini Fritters"

    };
    String[] categories = new String[]{
            "Dessert",
            "Entree",
            "Dessert",
            "Main",
            "Entree",
            "Main",
            "Dessert",
            "Main",
            "Dessert",
            "Drink",
            "Entree"
    };
    String[][] ingredients = new String[][]{
            new String[]{"Sugar", "Chocolate", "Cream"},
            new String[]{"Seafood"},
            new String[]{"Fruit", "Sugar"},
            new String[]{"Seafood"},
            new String[]{"Meat", "Fruit"},
            new String[]{"Vegetable", "Meat", "Pasta"},
            new String[]{"Fruit"},
            new String[]{"Meat", "Vegetable"},
            new String[]{"Fruit", "Sugar", "Chocolate"},
            new String[]{"Tea"},
            new String[]{"Vegetable"}
    };

    Q3Recipes recipes;

    @Before
    public void setup() {
        recipes = new Q3Recipes();
        addInitialRecipes(false);
    }

    private void addInitialRecipes(boolean duplicate) {
        for (int i = 0; i < quickRef.length; i++) {
            assertNotEquals("Q3VariantRecipes.addRecipe expected to return " + !duplicate + (duplicate ? " when adding a duplicate recipe " : " adding a recipe for the first time"),
                    duplicate, recipes.addRecipe(quickRef[i], recipeNames[i], categories[i], Set.of(ingredients[i])));
        }
    }

    private void addMoreRecipes(boolean duplicate) {
        assertNotEquals("Q3VariantRecipes.addRecipe expected to return " + !duplicate + (duplicate ? " when adding a duplicate recipe " : " adding a recipe for the first time"),
                duplicate, recipes.addRecipe("Burg67", "Burger", "Main", Set.of(new String[]{"Meat", "Vegetable", "Fruit"})));
        assertNotEquals("Q3VariantRecipes.addRecipe expected to return " + !duplicate + (duplicate ? " when adding a duplicate recipe " : " adding a recipe for the first time"),
                duplicate, recipes.addRecipe("Pota83", "Potato Soup", "Main", Set.of(new String[]{"Vegetable", "Cream"})));
    }

    @Test
    public void testAddAndCount() {
        Q3Recipes empty = new Q3Recipes();
        assertEquals("getRecipeCount() returned incorrect number of recipes", 0, empty.getRecipeCount());
        assertEquals("getRecipeCount() returned incorrect number of recipes", 11, recipes.getRecipeCount());
        addMoreRecipes(false);
        assertEquals("getRecipeCount() returned incorrect number of recipes", 13, recipes.getRecipeCount());
    }

    @Test
    public void testAddAndCountDuplicate() {
        addInitialRecipes(true);
        assertEquals("getRecipeCount() returned incorrect number of recipes", 11, recipes.getRecipeCount());
        addMoreRecipes(false);
        addMoreRecipes(true);
        assertEquals("getRecipeCount() returned incorrect number of recipes", 13, recipes.getRecipeCount());
    }

    @Test
    public void testDelete() {
        assertEquals("getRecipeCount() returned incorrect number of recipes", 11, recipes.getRecipeCount());
        assertTrue("Q3VariantRecipes.deleteRecipe expected to return true for recipe that exists in collection", recipes.deleteRecipe(quickRef[0]));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 10, recipes.getRecipeCount());
        assertTrue("Q3VariantRecipes.deleteRecipe expected to return true for recipe that exists in collection", recipes.deleteRecipe(quickRef[1]));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 9, recipes.getRecipeCount());
        assertFalse("Q3VariantRecipes.deleteRecipe expected to return false for recipe that does not appear in collection", recipes.deleteRecipe(quickRef[1]));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 9, recipes.getRecipeCount());
        assertEquals("getRecipeCount() returned incorrect number of recipes", 9, recipes.getRecipeCount());
    }

    @Test
    public void testDeleteNonExistent() {
        final String NON_EXISTENT_RECIPE = "Banana Bread";

        Q3Recipes empty = new Q3Recipes();
        assertFalse("Q3VariantRecipes.deleteRecipe expected to return false for empty collection", empty.deleteRecipe(NON_EXISTENT_RECIPE));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 0, empty.getRecipeCount());

        assertFalse("Q3VariantRecipes.deleteRecipe expected to return false for non-existent recipe", recipes.deleteRecipe(NON_EXISTENT_RECIPE));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 11, recipes.getRecipeCount());
        assertTrue("Q3VariantRecipes.deleteRecipe expected to return true for recipe that exists in collection", recipes.deleteRecipe(quickRef[0]));
        assertFalse("Q3VariantRecipes.deleteRecipe expected to return false for empty collection", recipes.deleteRecipe(quickRef[0]));
        assertEquals("getRecipeCount() returned incorrect number of recipes", 10, recipes.getRecipeCount());
    }

    @Test
    public void testGetRecipesForCategoryEmpty() {
        assertNotNull("getRecipesForCategory(\"Supper\") returned null, expected empty set", recipes.getRecipesForCategory("Supper"));
        assertTrue("getRecipesForCategory(\"Supper\") returned non-empty set, expected empty", recipes.getRecipesForCategory("Supper").isEmpty());
        assertFalse("getRecipesForCategory(\"Main\") returned empty set, expected non-empty", recipes.getRecipesForCategory("Main").isEmpty());
    }

    @Test
    public void testGetRecipesForCategory() {
        checkCategory("Dessert", Set.of("Chocolate Fudge", "Toffee Apple", "Fruit Salad", "Apple Pie"));
        checkCategory("Entree", Set.of("Prawn Skewers", "Duck Salad", "Zucchini Fritters"));
        checkCategory("Main", Set.of("Tuna Mornay","Spaghetti", "Curry"));
        checkCategory("Drink", Set.of("Green Tea"));
        addMoreRecipes(false);
        checkCategory("Main", Set.of("Tuna Mornay","Spaghetti", "Curry", "Potato Soup", "Burger"));
    }

    @Test
    public void testGetRecipesForCategoryDuplicate() {
        addInitialRecipes(true);
        checkCategory("Main", Set.of("Tuna Mornay","Spaghetti", "Curry"));
        checkCategory("Drink", Set.of("Green Tea"));
        checkCategory("Dessert", Set.of("Chocolate Fudge", "Toffee Apple", "Fruit Salad", "Apple Pie"));
        checkCategory("Entree", Set.of("Prawn Skewers", "Duck Salad", "Zucchini Fritters"));
    }

    @Test
    public void testGetRecipesForCategoryDelete() {
        recipes.deleteRecipe("Appl67");
        recipes.deleteRecipe("Tuna88");
        checkCategory("Dessert", Set.of("Chocolate Fudge", "Fruit Salad", "Toffee Apple"));
        checkCategory("Main", Set.of("Spaghetti", "Curry"));
    }

    private void checkCategory(String category, Set<String> expected) {
        assertEquals("getRecipesForCategory(\"" + category + "\") returned incorrect set", expected, recipes.getRecipesForCategory(category));
    }

    @Test
    public void testGetRecipesContaining() {
        assertNotNull("getRecipesContaining(\"Xx\") returned null, expected empty set", recipes.getRecipesContaining("Xx"));
        assertTrue("getRecipesContaining(\"Xx\") returned non-empty set, expected empty", recipes.getRecipesContaining("Xx").isEmpty());
        assertFalse("getRecipesContaining(\"Meat\") returned empty set, expected non-empty", recipes.getRecipesContaining("Meat").isEmpty());
        checkIngredient("Meat", Set.of("Spaghetti", "Duck Salad", "Curry"));
        checkIngredient("Fruit",  Set.of("Toffee Apple", "Duck Salad", "Fruit Salad",  "Apple Pie"));
        checkIngredient("Tea", Set.of("Green Tea"));
        checkIngredient("Vegetable", Set.of("Spaghetti", "Curry", "Zucchini Fritters"));
        addMoreRecipes(false);
        checkIngredient("Meat", Set.of("Spaghetti", "Duck Salad", "Curry", "Burger"));
        checkIngredient("Fruit",  Set.of("Toffee Apple", "Duck Salad", "Burger", "Fruit Salad",  "Apple Pie"));
        checkIngredient("Vegetable", Set.of("Spaghetti", "Curry", "Zucchini Fritters", "Burger", "Potato Soup"));
        checkIngredient("Seafood", Set.of("Tuna Mornay", "Prawn Skewers"));
    }

    @Test
    public void testGetRecipesContainingDuplicate() {
        addMoreRecipes(false);
        addInitialRecipes(true);
        addMoreRecipes(true);
        checkIngredient("Meat", Set.of("Spaghetti", "Duck Salad", "Curry", "Burger"));
        checkIngredient("Fruit",  Set.of("Toffee Apple", "Duck Salad", "Burger", "Fruit Salad",  "Apple Pie"));
        checkIngredient("Tea", Set.of("Green Tea"));
        checkIngredient("Vegetable", Set.of("Spaghetti", "Curry", "Zucchini Fritters", "Burger", "Potato Soup"));
    }

    @Test
    public void testGetRecipesContainingDelete() {
        recipes.deleteRecipe(quickRef[5]);
        checkIngredient("Meat",  Set.of("Duck Salad", "Curry"));
        checkIngredient("Vegetable", Set.of("Curry", "Zucchini Fritters"));
        checkIngredient("Pasta", Set.of());
    }

    private void checkIngredient(String ingredient, Set<String> expected) {
        assertEquals("getRecipesContaining(\"" + ingredient + "\") returned incorrect set", expected, recipes.getRecipesContaining(ingredient));
    }

    @Test
    public void testGetMaxRecipesForIngredient() {
        Q3Recipes empty = new Q3Recipes();
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 0, empty.getMaxRecipesForIngredient());
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 4, recipes.getMaxRecipesForIngredient());
    }

    @Test
    public void testGetMaxRecipesForIngredientDuplicate() {
        addInitialRecipes(true);
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 4, recipes.getMaxRecipesForIngredient());
    }

    @Test
    public void testGetMaxRecipesForIngredientDelete() {
        recipes.deleteRecipe("Toff67");
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 3, recipes.getMaxRecipesForIngredient());
        recipes.deleteRecipe("Curr87");
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 3, recipes.getMaxRecipesForIngredient());
        recipes.deleteRecipe("Appl67");
        assertEquals("getMaxRecipesForIngredient() returned incorrect value", 2, recipes.getMaxRecipesForIngredient());
    }

    @Test
    public void testGetNumCrossCategoryIngredients() {
        assertEquals("getNumCrossCategoryIngredients() returned incorrect value", 4, recipes.getNumCrossCategoryIngredients());
        addMoreRecipes(false);
        assertEquals("getNumCrossCategoryIngredients() returned incorrect value", 5, recipes.getNumCrossCategoryIngredients());
    }

    @Test
    public void testGetNumCrossCategoryIngredientsDuplicate() {
        addInitialRecipes(true);
        assertEquals("getNumCrossCategoryIngredients() returned incorrect value", 4, recipes.getNumCrossCategoryIngredients());
        addMoreRecipes(false);
        addMoreRecipes(true);
        assertEquals("getNumCrossCategoryIngredients() returned incorrect value", 5, recipes.getNumCrossCategoryIngredients());
    }

    @Test
    public void testGetNumCrossCategoryIngredientsDelete() {
        recipes.deleteRecipe("Choc67");
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 4, recipes.getNumCrossCategoryIngredients());
        recipes.deleteRecipe("Duck66");
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 2, recipes.getNumCrossCategoryIngredients());
        recipes.deleteRecipe("Praw82");
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 1, recipes.getNumCrossCategoryIngredients());
    }

    @Test
    public void testGetNumCategoriesContaining() {
        assertEquals("getNumCategoriesContaining() returned incorrect value", 1, recipes.getNumCategoriesContaining("Sugar"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 2, recipes.getNumCategoriesContaining("Meat"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 2, recipes.getNumCategoriesContaining("Fruit"));
    }

    @Test
    public void testGetNumCategoriesContainingDuplicate() {
        addInitialRecipes(true);
        assertEquals("getNumCategoriesContaining() returned incorrect value", 1, recipes.getNumCategoriesContaining("Sugar"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 2, recipes.getNumCategoriesContaining("Meat"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 2, recipes.getNumCategoriesContaining("Fruit"));
    }

    @Test
    public void testGetNumCategoriesContainingDelete() {
        recipes.deleteRecipe("Duck66");
        assertEquals("getNumCategoriesContaining() returned incorrect value", 1, recipes.getNumCategoriesContaining("Sugar"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 1, recipes.getNumCategoriesContaining("Meat"));
        assertEquals("getNumCategoriesContaining() returned incorrect value", 1, recipes.getNumCategoriesContaining("Fruit"));
    }
}
