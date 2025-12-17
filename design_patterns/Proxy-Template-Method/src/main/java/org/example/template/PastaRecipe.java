package org.example.template;

public class PastaRecipe extends CookingRecipe {
    @Override
    protected void prepareIngredients() {
        System.out.println("Preparing pasta, tomato sauce, and cheese.");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling water, cooking pasta, and adding sauce.");
    }
}
