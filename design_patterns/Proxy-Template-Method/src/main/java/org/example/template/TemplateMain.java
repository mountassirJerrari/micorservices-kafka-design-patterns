package org.example.template;

public class TemplateMain {
    public static void main(String[] args) {
        System.out.println("--- Template Pattern Demonstration ---");
        
        System.out.println("\nMaking Pasta:");
        CookingRecipe pasta = new PastaRecipe();
        pasta.cookRecipe();

        System.out.println("\nMaking Pizza:");
        CookingRecipe pizza = new PizzaRecipe();
        pizza.cookRecipe();
    }
}
