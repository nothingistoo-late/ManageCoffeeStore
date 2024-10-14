package Management;

import CoffeeStore.Ingredients;
import Services.Report;
import Services.drinkService;
import Services.ingredientService;
import Services.orderService;
import java.util.ArrayList;
import tool.Inputter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ntphu
 */
public class Management {

    public ingredientService ingredientService = new ingredientService();
    public drinkService drinkService = new drinkService();
    public orderService orderService = new orderService();
    public Report Report = new Report();
    String ingredientFile = "Ingredients.dat",
            menuFile = "Menu.dat",
            orderFile = "Order.dat";

    public void ingredientManagement() {
        int pick = 0;
        do {
            System.out.println("------Ingredient Management-------\n"
                    + "1 - Add an ingredient\n"
                    + "2 - Update ingredient information\n"
                    + "3 - Delete ingredient\n"
                    + "4 - Show all the ingredients\n"
                    + "5 - Exit.");
            pick = Inputter.getAnInteger("Enter Your Choice := ", "Choice must be 1=>choice<=5", 1, 5);
            switch (pick) {
                case 1:
                    ingredientService.addIngredient();
                    break;
                case 2:
                    ingredientService.updateIngredient();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 3:
                    ingredientService.deleteIngredient();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 4:
                    ingredientService.showIngredient();
                    break;
                case 5:
                    System.out.println("Exit To Main Screen!!");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
            }
        } while (pick != 5);
    }

    public void menuManagement() {
        int pick = 0;
        do {
            System.out.println("------Drink Management-------\n"
                    + "1 - Add an Drink \n"
                    + "2 - Update Drink information\n"
                    + "3 - Delete Drink\n"
                    + "4 - Show all the Drink\n"
                    + "5 - Exit.");
            pick = Inputter.getAnInteger("Enter Your Choice := ", "Choice must be 1=>choice<=5", 1, 5);
            switch (pick) {
                case 1:
                    drinkService.addDrink();
                    break;
                case 2:
                    drinkService.updateDrink();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 3:
                    drinkService.deleteDrink();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 4:
                    drinkService.showDrink();
                    break;
                case 5:
                    System.out.println("Exit To Main Screen!!");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
            }
        } while (pick != 5);
    }

    public void orderManagement() {
        int pick = 0;
        do {
            System.out.println("------Order Management-------\n"
                    + "1 - Dispensing the drink \n"
                    + "2 - Update the dispensing drink\n"
                    + "3 - Exit.");
            pick = Inputter.getAnInteger("Enter Your Choice := ", "Choice must be 1=>choice<=3", 1, 3);
            switch (pick) {
                case 1:
                    orderService.MakeOrder();
                    System.out.println("Exit To Main Menu!!");
                    pick = 3;
                    break;
                case 2:
                    orderService.UpdateDrink();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 3:
                    System.out.println("Exit To Main Menu!!!");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
            }
        } while (pick != 3);

    }

    public void report() {
        int pick = 0;
        do {
            System.out.println("------Report-------\n"
                    + "1 - The ingredients are available \n"
                    + "2 - The drinks for which the store is out of ingredients\n"
                    + "3 - Show all the dispensing drink\n"
                    + "4 - Exit.");
            pick = Inputter.getAnInteger("Enter Your Choice := ", "Choice must be 1=>choice<=4", 1, 4);
            switch (pick) {
                case 1:
                    Report.ingredientAvailable();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 2:
                    Report.DrinkOutIngredient();
                    System.out.println("Exit To Main Menu!!");
                    pick = 5;
                    break;
                case 3:
                    Report.ShowDispensingDrink();
                    pick = 5;
                    break;
                case 4:
                    System.out.println("Exit To Main Menu!!!");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
            }
        } while (pick != 4);
    }

    public void saveData() {
        ingredientService.saveData(ingredientFile);
        drinkService.saveData(menuFile);
        orderService.saveData(orderFile);
    }

    public void loadData() {
        ingredientService.loadData(ingredientFile);
        drinkService.loadData(menuFile);
        orderService.loadData(orderFile);
    }
}
