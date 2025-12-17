package org.example.template;

public abstract class CookingRecipe {
    // Template method
    public final void cookRecipe() {
        prepareIngredients();
        cook();
        serve();
    }

    protected abstract void prepareIngredients();
    protected abstract void cook();
    
    private void serve() {
        System.out.println("Serving the dish. Enjoy!");
    }
}
