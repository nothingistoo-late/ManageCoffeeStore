/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CoffeeStore.Ingredients;
import CoffeeStore.Menu;
import CoffeeStore.Order;
import static Services.drinkService.listDrink;
import static Services.ingredientService.listIngredients;
import java.util.ArrayList;

/**
 *
 * @author ntphu
 */
public class Report extends orderService {

    public void ingredientAvailable() {
        ArrayList<Ingredients> tam = new ArrayList<>();
        if (listIngredients.isEmpty()) {
            System.out.println("No Data !!!!");
        } else {
            for (Ingredients a : listIngredients) {
                int o1 = Integer.parseInt(a.getQuantityIngredient());
                if (o1 > 0) {
                    tam.add(a);
                }
            }
        }
        if (tam.isEmpty()) {
            System.out.println("No Ingredient Run Out!!");
        } else {
            System.out.println("-------Ingredient Information--------");
            System.out.println("| Code|           Name|Quantity| Unit|");
            for (Ingredients a : tam) {
                System.out.println(a);
            }
            System.out.println("-------------------------------------");
        }
    }

    public void DrinkOutIngredient() {
        Boolean kt = false;
        if (listDrink.isEmpty()) {
            System.out.println("No Data!!!!");
        } else {
            for (Menu i : listDrink.values()) {
                for (Ingredients j : i.getIngredients()) {
                    for (Ingredients l : listIngredients) {
                        if (l.getCodeIngredient().equalsIgnoreCase(j.getCodeIngredient())) {
                            int o1 = Integer.parseInt(j.getQuantityIngredient());
                            int o2 = Integer.parseInt(l.getQuantityIngredient());
                            if (o2 < o1) {

                                if (kt = false) {
                                    System.out.println("-------------Drink Information----------------");
                                    System.out.println("| Code|      Name     |   Beverage Recipes    |");
                                }
                                System.out.print(i);
                                kt = true;
                                for (Ingredients x : i.getIngredients()) {
                                    System.out.print(x.getQuantityIngredient() + ":" + x.getNameIngredient() + ",");
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
        if (kt == false) {
            System.out.println("No Drinks Run Out Of Ingredients");
        }
    }

    public void ShowDispensingDrink() {
        if (!ListOrder.isEmpty()) {
            for (Order i : ListOrder.values()) {
                System.out.println(i);
                if (i.getDsDrink().isEmpty()) {
                    System.out.println("dsDrink emty!");
                }
                for (Menu j : i.getDsDrink().values()) {
                    System.out.print(j.getNameMenu() + " SL: " + j.getQuantityDrink() + ",");
                }
                System.out.println();
            }
        } else {
            System.out.println("No Data!!!");
        }
    }

}
