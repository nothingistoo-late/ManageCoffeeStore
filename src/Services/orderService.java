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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tool.Inputter;
import tool.Tool;

/**
 *
 * @author ntphu
 */
public class orderService extends HashMap<String, Order> {

    public static HashMap<String, Order> ListOrder = new HashMap<>();
    public HashMap<String, Menu> DrinkOrder = new HashMap<>();
    public drinkService drinkService = new drinkService();
    public ingredientService IngredientService = new ingredientService();
    public void MakeOrder() {
        
        String pick = "", a = "", s = "";
        do {
            Order x = new Order();
            do {
                x.setOrderCode(Inputter.getString("Enter Order Code Start With 'O' := ", "Order Code Must Start With O\n"
                        + "Example O01,O02,OXX..", "^O\\d.*"));
                if (ListOrder.containsKey(x.getOrderCode())) {
                    System.out.println("Valid Order Code !!!\n"
                            + "Please ReInput!!!");
                }
            } while (ListOrder.containsKey(x.getOrderCode()));

            x.setOrderName(Inputter.getString("Enter Order Name := ", "Order Name Cant Be Emty!!"));

            if (!listDrink.isEmpty()) {
                System.out.println("The Following Is A List Of Available Drinks :\n");
                drinkService.showDrink();
                do {
                    do {
                        a = (Inputter.getString("Enter Drink Code Start With 'D' To Add To Order := ", "Drink Code Must Star With D\n"
                                + "Example D01,D02,DXX..", "^D\\d.*"));
                        if ((!listDrink.containsKey(a))
                                || ListOrder.containsKey(a)) {
                            System.out.println("Invalid Drink Code !!!\n"
                                    + "Please ReEnter!!!");
                        }
                    } while ((!listDrink.containsKey(a))
                            || ListOrder.containsKey(a));
                    int quantityOrder = Inputter.getAnInteger("Enter Quantity Of Drink := ", "Quantity Of Drink Cant Be Emty And 0<Quantity<=1000!!", 0, 1000);
                    Menu d = listDrink.get(a);
                    int cal = 0;
                    int min = 1000000;
                    for (Ingredients i : d.getIngredients()) {
                        for (Ingredients j : IngredientService.listIngredients) {
                            if (j.getCodeIngredient().equalsIgnoreCase(i.getCodeIngredient())) {
                                int o1 = Integer.parseInt(j.getQuantityIngredient());
                                int o2 = Integer.parseInt(i.getQuantityIngredient());
                                if (o1 > o2 * quantityOrder) 
                                    cal = quantityOrder;
                                if (o1 < o2 * quantityOrder) 
                                    cal = (int) (o1 / o2);
                                if (cal < min) {
                                    min = cal;
                                }
                                break;
                            }
                        }
                    }
                    if (min == quantityOrder || min > quantityOrder) {
                        d.setQuantityDrink(min);
                        System.out.println("Added Drink To Order!!!");
                    }
                    if (min < quantityOrder) {
                        System.out.println("Just Can Make " + min + " Drinks!!!");
                        String Select = Inputter.getString("Do You Want To Add Or Not ???\n"
                                + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
                        if (Select.equalsIgnoreCase("y")) {
                            d.setQuantityDrink(min);
                            for (Ingredients i : d.getIngredients()) {
                                for (Ingredients j : IngredientService.listIngredients) {
                                    if (j.getCodeIngredient().equalsIgnoreCase(i.getCodeIngredient())) {
                                        int o1 = Integer.parseInt(j.getQuantityIngredient());
                                        int o2 = Integer.parseInt(i.getQuantityIngredient());
                                        o1 = o1 - (min * o2);
                                        j.setQuantityIngredient(String.valueOf(o1));
                                        break;
                                    }
                                }
                            }
                            System.out.println("Added Drink To Order!!!");
                        } else {
                            System.out.println("You Canceled!!");
                        }
                    }
                    listDrink.get(a).setQuantityDrink(quantityOrder);
                    DrinkOrder.put(a, listDrink.get(a));
                    pick = Inputter.getString("Do You Want To Add More Drinks ???\n"
                            + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
                } while (pick.equalsIgnoreCase("Y"));
                x.setDsDrink(new HashMap<>(DrinkOrder));
                DrinkOrder.clear();
                String st = Inputter.getString("Do You Really Want To Add This Orders??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
                if (st.equalsIgnoreCase("y")) {
                    System.out.println("Add Successfull Order!!");
                    ListOrder.put(x.getOrderCode(), x);
                } else {
                    System.out.println("You Canceled!!!");
                }
            } else {
                System.out.println("No Drink In List!!!");
                s = "N";
            }
            if (s.equalsIgnoreCase("y")) {
                s = Inputter.getString("Do You Want To Continue To Add New Drinks??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
            }
            pick = Inputter.getString("Do You Want To Continue Add New Orders??? \n"
                    + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
        } while (pick.equalsIgnoreCase("y"));
    }

    public void UpdateDrink() {
        String pick = "";
        String a = "";
        String tg="";
        do {
            do{
            tg = Inputter.getString("Enter Order Code Start With 'O' to Update := ", "Order Code Must Start With O\n"
                    + "Example O01,O02,OXX..", "^O\\d.*");
            if (!ListOrder.containsKey(tg))
                    System.out.println("Invalid Order !!!\n"
                                     + "Please ReEnter := ");
            }while (!ListOrder.containsKey(tg));
            Order update = ListOrder.get(tg);
            System.out.println(update);
            if (update.getDsDrink().isEmpty()) {
                System.out.println("Drinks List Is Emty!!");
            }
            for (Menu j : update.getDsDrink().values()) {
                System.out.println(j.getNameMenu() + " SL: " + j.getQuantityDrink() + ",");
                a = Tool.inputString("Enter New Quantity Of Drink (Or Blank To Not Update):= ");
                int quantityOrder = Integer.parseInt(a);
                if ((!a.isEmpty() && !a.trim().isEmpty())) {
                    int cal = 0;
                    int min = 1000000;
                    for (Ingredients i : j.getIngredients()) {
                        for (Ingredients l : IngredientService.listIngredients) {
                            if (l.getCodeIngredient().equalsIgnoreCase(i.getCodeIngredient())) {
                                int o1 = Integer.parseInt(l.getQuantityIngredient());
                                int o2 = Integer.parseInt(i.getQuantityIngredient());
                                if (o1 > o2 * quantityOrder) 
                                    cal = quantityOrder;
                                if (o1 < o2 * quantityOrder) 
                                    cal = (int) (o1 / o2);
                                if (cal < min) {
                                    min = cal;
                                }
                                break;
                            }
                        }
                    }
                    if (min == quantityOrder || min > quantityOrder) {
                        j.setQuantityDrink(min);
                        System.out.println("Updated Quantity Drink To Order!!!");
                    }
                    if (min < quantityOrder) {
                        System.out.println("Just Can Make " + min + " Drinks!!!");
                        String Select = Inputter.getString("Do You Want To Update Or Not ???\n"
                                + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
                        if (Select.equalsIgnoreCase("y")) {
                            j.setQuantityDrink(min);
                            for (Ingredients i : j.getIngredients()) {
                                for (Ingredients l : IngredientService.listIngredients) {
                                    if (l.getCodeIngredient().equalsIgnoreCase(i.getCodeIngredient())) {
                                        int o1 = Integer.parseInt(l.getQuantityIngredient());
                                        int o2 = Integer.parseInt(i.getQuantityIngredient());
                                        o1 = o1 - (min * o2);
                                        l.setQuantityIngredient(String.valueOf(o1));
                                        break;
                                    }
                                }
                            }
                            System.out.println("Updated Quantity Drink To Order!!!");
                        } else {
                            System.out.println("You Canceled!!");
                        }
                    }
                } else 
                    System.out.println("You Don't Change Anything !!");
                
            }
            pick = Inputter.getString("Do You Want To Continue Update Order??? \n"
                    + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
        } while (pick.equalsIgnoreCase("y"));
    }

    public void saveData(String file) {
        FileOutputStream fos = null;
        try {
            File f = new File(file);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Order x : ListOrder.values()) {
                oos.writeObject(x);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ingredients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ingredients.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Ingredients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadData(String fileName) {
        File f = new File(fileName);
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Order y = (Order) ois.readObject();
                ListOrder.put(y.getOrderCode(), y);
            }
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Khong Ton Tai File!!");
        } catch (IOException ex) {
            Logger.getLogger(Ingredients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ingredients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
