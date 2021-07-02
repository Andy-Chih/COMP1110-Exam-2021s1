package comp1110.exam;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.concurrent.TimeUnit;

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

}


