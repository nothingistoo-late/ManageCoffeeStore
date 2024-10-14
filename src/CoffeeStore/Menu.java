/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoffeeStore;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ntphu
 */
public class Menu implements Serializable {

    private String codeMenu, nameMenu;
    private List<Ingredients> ingredients;
    private int quantityDrink;

    public Menu() {
    }

    public Menu(String codeMenu, String nameMenu, List<Ingredients> ingredient, int QuantityDrink) {
        this.codeMenu = codeMenu;
        this.nameMenu = nameMenu;
        this.ingredients = ingredients;
        this.quantityDrink = quantityDrink;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public int getQuantityDrink() {
        return quantityDrink;
    }

    public void setQuantityDrink(int quantityDrink) {
        this.quantityDrink = quantityDrink;
    }

    @Override
    public String toString() {
        return String.format("|%5s|%15s|", this.codeMenu, this.nameMenu);
    }

}
