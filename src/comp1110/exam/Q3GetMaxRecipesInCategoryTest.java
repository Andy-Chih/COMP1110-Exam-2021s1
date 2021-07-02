package comp1110.exam;

import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * COMP1110 Exam, Question 3.2
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
@org.junit.jupiter.api.Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class Q3GetMaxRecipesInCategoryTest {
    // FIXME add one or more JUnit tests for the getMaxRecipesInCategory() method of the Q3Recipes class
    Q3Recipes recipes;

    String[] quickRef = new String[]{
            "Choc67",
            "Praw82",
            "Toff67",
            "Tuna88",
            "Duck66",
            "Spag70",
    };

    String[] recipeNames = new String[]{
            "Chocolate Fudge",
            "Prawn Skewers",
            "Toffee Apple",
            "Tuna Mornay",
            "Duck Salad",
            "Spaghetti",
    };
    String[] categories = new String[]{
            "Dessert",
            "Entree",
            "Dessert",
            "Main",
            "Entree",
            "Main",

    };
    String[][] ingredients = new String[][]{
            new String[]{"Sugar", "Chocolate"},
            new String[]{"Seafood"},
            new String[]{"Fruit", "Sugar"},
            new String[]{"Seafood"},
            new String[]{"Meat", "Fruit"},
            new String[]{"Vegetable", "Meat", "Pasta"},
    };

    /**
     * For example, if there are four ---recipes---- in this collection:
     *      * - "Fudg67" (category: Dessert) contains "Sugar", "Butter"
     *      * - "Praw77" (category: Entree) contains "Seafood"
     *      * - "Toff67" (category: Dessert) contains "Fruit" and "Sugar"
     *      * - "Tuna76" (category: Main) contains "Seafood"
     *      * then getMaxRecipesInCategory() == 2 (for the category "Dessert")
     */




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

    private void addMoreRecipes1() {
        recipes.addRecipe(quickRef[0],recipeNames[0],categories[0],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[1],recipeNames[1],categories[1],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[2],recipeNames[1],categories[2],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[3],recipeNames[1],categories[3],Set.of(ingredients[0]));
    }

    private void addMoreRecipes2() {
        recipes.addRecipe(quickRef[0],recipeNames[0],categories[0],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[3],recipeNames[1],categories[2],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[1],recipeNames[1],categories[2],Set.of(ingredients[0]));
    }

    private void addMoreRecipes3() {
        recipes.addRecipe(quickRef[0],recipeNames[0],categories[0],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[1],recipeNames[1],categories[0],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[2],recipeNames[1],categories[1],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[3],recipeNames[1],categories[1],Set.of(ingredients[0]));
        recipes.addRecipe(quickRef[4],recipeNames[1],categories[0],Set.of(ingredients[0]));
    }

    @Test
    public void test1Empty(){
        Q3Recipes recipes = new Q3Recipes();
        assertEquals("getMaxRecipesInCategory() expected to return ", 0, recipes.getMaxRecipesInCategory());
    }

    @Test
    public void test2(){
        Q3Recipes recipes = new Q3Recipes();
        addMoreRecipes1();
        assertEquals("getMaxRecipesInCategory() expected to return ", 1, recipes.getMaxRecipesInCategory());
    }

    @Test
    public void test3(){
        Q3Recipes recipes = new Q3Recipes();
        addMoreRecipes2();
        assertEquals("getMaxRecipesInCategory() expected to return ", 2, recipes.getMaxRecipesInCategory());
    }

    @Test
    public void test4(){
        Q3Recipes recipes = new Q3Recipes();
        addMoreRecipes3();
        assertEquals("getMaxRecipesInCategory() expected to return ", 3, recipes.getMaxRecipesInCategory());
    }


}


