package org.example.template;

public class PizzaRecipe extends CookingRecipe {
    @Override
    protected void prepareIngredients() {
        System.out.println("Preparing dough, tomato sauce, cheese, and toppings.");
    }

    @Override
    protected void cook() {
        System.out.println("Baking pizza in the oven at 200 degrees Celsius.");
    }
}
