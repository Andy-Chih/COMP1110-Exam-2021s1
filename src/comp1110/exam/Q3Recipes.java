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
    // main set
    public Set<Recipe> reSet = new HashSet<>();

    // field
    public class Recipe{
        String quickRef;
        String name;
        String category;
        Set<String> ingredients;

        public Recipe(String quickRef, String name, String category, Set<String> ingredients) {
            this.quickRef = quickRef;
            this.name = name;
            this.category = category;
            this.ingredients = ingredients;
        }
    }

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
        Recipe re = new Recipe( quickRef, name, category,  ingredients);
        // check contains
        for(Recipe r: reSet){
            if (Objects.equals(r.quickRef,quickRef)){
                return false;
            }
        }

        this.reSet.add(re);
        return true;
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
        if (quickRef==null) return false;
        for(Recipe r: reSet){
            if (Objects.equals(r.quickRef,quickRef)){
                reSet.remove(r);
                return true;
            }
        }

        return false;
    }

    /**
     * @return the total number of recipes in this collection
     */
    public int getRecipeCount() {
        // FIXME complete this method
        return this.reSet.size();
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
        Set<String> names = new HashSet<>();
        if (ingredient==null) return names;

        for(Recipe r: reSet){
            for (String s :r.ingredients){
                if (Objects.equals(s,ingredient)){
                    names.add(r.name);
                }
            }
        }

        return names;
    }

    /**
     * Gets the set of names of all recipes for the given category.--------------------------------------------别忘了
     *
     * @param category the name of the category to search for
     * @return the set of names of all recipes in the given category
     * (if there are no recipes for the category, this will be the empty set)
     */
    public Set<String> getRecipesForCategory(String category) {
        Set<String> result = new HashSet<>();
        if (category==null) return result;

        for(Recipe r: reSet){
            if(Objects.equals(r.category,category)){
                result.add(r.name);
            }
        }
        // FIXME complete this method
        return result;
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
        Map<String,Set<String>> ingreRe = new HashMap<>();

        for (Recipe r: reSet){
            for (String s: r.ingredients){
                // check if exist
                if (!(ingreRe.keySet().contains(s))){
                    Set<String> inner = new HashSet<>();
                    inner.add(r.name);
                    ingreRe.put(s,inner);
                }
                Set<String> old = ingreRe.get(s);
                old.add(r.name);
                ingreRe.put(s,old);
                //

            }
        }

        int max = 0;
        Collection<Set<String>> demo = ingreRe.values();
        for(Set<String> se: demo){
            if(se.size()>=max){
                max=se.size();
            }
        }

        return max;
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
        if (ingredient==null) return 0;

        Set<String> aSet = new HashSet<>();
        for(Recipe r: reSet){
            if(r.ingredients.contains(ingredient)){
                aSet.add(r.category);
            }
        }


        return aSet.size();
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
        Map<String,Set<String>> ingreCate = new HashMap<>();

        //Seafood----"Entree"/ "Main"
        for (Recipe r: reSet){
            for (String s: r.ingredients){
                // check if exist
                if (!(ingreCate.keySet().contains(s))){
                    Set<String> inner = new HashSet<>();
                    inner.add(r.category);
                    ingreCate.put(s,inner);
                }
                Set<String> old = ingreCate.get(s);
                old.add(r.category);
                ingreCate.put(s,old);
            }
        }

        int sum=0;
        Collection<Set<String>> demo = ingreCate.values();
        for(Set<String> se: demo){
            if (se.size()>=2){
                sum++;
            }
        }




        return sum;
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


