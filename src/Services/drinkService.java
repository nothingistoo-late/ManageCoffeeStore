/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CoffeeStore.Ingredients;
import CoffeeStore.Menu;
import Management.Management;
import static Services.ingredientService.listIngredients;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tool.Inputter;
import tool.Tool;

/**
 *
 * @author ntphu
 */
public class drinkService extends HashMap<String, Menu> {

    public static HashMap<String, Menu> listDrink = new HashMap<>();
    public ArrayList<Ingredients> drinkIngredients = new ArrayList<>();
    public ingredientService IngredientService = new ingredientService();

    public void addDrink() {
        showDrink();
        String s = "Y", pick = "Y";
        String a = "";
        do {
            Menu x = new Menu();
            do {
                x.setCodeMenu(Inputter.getString("Enter Drink Code Start With 'D' := ", "Drink Code Must Start"
                        + " With 'D'\n"
                        + "Example D01,D02,DXX..", "^D\\d.*"));
                if (listDrink.containsKey(x.getCodeMenu())) {
                    System.out.println("Valid Drink Code !!!\n"
                            + "Please ReEnter!!!");
                }
            } while (listDrink.containsKey(x.getCodeMenu()));

            x.setNameMenu(Inputter.getString("Enter Drink Name := ", "Drink Name Cant Be Emty!!"));

            if (!listIngredients.isEmpty()) {
                System.out.println("There are Available Ingredent :\n");
                IngredientService.showIngredient();
                do {
                    do {
                        a = (Inputter.getString("Enter Ingredient Code Start With 'I' To Make Drink := ", "Ingredient Code Must Start With I\n"
                                + "Example I01,I02,IXX..", "^I\\d.*"));
                        if (!ingredientService.checkID(a, listIngredients)
                                || ingredientService.checkID(a, drinkIngredients)) {
                            System.out.println("Invalid Ingredient Code !!!\n"
                                    + "Please ReEnter!!!");
                        }
                    } while (!ingredientService.checkID(a, listIngredients)
                            || ingredientService.checkID(a, drinkIngredients));
                    for (Ingredients d : listIngredients) {
                        if (d.getCodeIngredient().equalsIgnoreCase(a)) {
                            Ingredients tam = d;
                            tam.setQuantityIngredient(Inputter.getString("Enter Quantity Of Ingredient To Make Drink := ", "Quantity Must Be Number And >0!!", "^[1-9]\\d*$"));
                            drinkIngredients.add(tam);
                            System.out.println("Added Ingredient To Beverage Recipe!!!");
                            break;
                        }
                    }
                    pick = Inputter.getString("Do You Want To Add More Ingredient ???\n"
                            + "Enter Your Choice [Y/N] := ", "^[NnYy]$");
                } while (pick.equalsIgnoreCase("Y"));
                x.setIngredients(new ArrayList<>(drinkIngredients));
                drinkIngredients.clear();
                pick = Inputter.getString("Do You Want To Add This Beverage Recipe??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
                if (pick.equalsIgnoreCase("Y")) {
                    System.out.println("Add Successfull Beverage Recipe!!");
                    listDrink.put(x.getCodeMenu(), x);
                } else {
                    System.out.println("You Canceled!!!");
                }

            } else {
                System.out.println("No Ingredient In List!!!");
                s = "N";
            }
            if (s.equalsIgnoreCase("y")) {
                s = Inputter.getString("Do You Want To Continue To Add New Drinks??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
            }
        } while (s.equalsIgnoreCase("Y"));

    }

    public void updateDrink() {
        String s = "Y", pick = "Y", st = "";
        Menu x = new Menu();
        boolean kt = false;
        System.out.println("There Are Available Drink:");
        showDrink();
        do {
            do {
                st = (Inputter.getString("Enter Drink Code Start With 'D' To Update := ", "Drink Code Must Start With 'D'\n"
                        + "Example D01,D02,DXX..", "^D\\d.*"));
                if (!listDrink.containsKey(st)) {
                    System.out.println("Invalid Drink Code !!!\n"
                            + "Please ReEnter!!!");
                }
            } while (!listDrink.containsKey(st));

            x = listDrink.get(st);

            st = Tool.inputString("Enter New Drink Name := ");
            if (!st.isEmpty() && !st.trim().isEmpty()) {
                x.setNameMenu(st);
            }
            if (x.getIngredients().isEmpty()) {
                System.out.println("List Ingredients Is Emty!!!!");
            } else {
                System.out.println("There Are Ingredients Of Drink:");
                System.out.println("-------Ingredient Information--------\n");
                System.out.println("| Code|           Name|Quantity| Unit|\n");
                for (Ingredients a : x.getIngredients()) {
                    System.out.println(a);
                }
                System.out.println("-------------------------------------\n");
            }
            do {
                do {
                    st = (Inputter.getString("Enter Blank To Not Update \n"
                            + "Or Enter Ingredient Code Start With 'I' To Update := ", "Ingredient Code Must Start With 'I'\n"
                            + "Example I01,I02,IXX..", "^I\\d.*"));
                    if (!ingredientService.checkID(st, (ArrayList<Ingredients>) x.getIngredients())) {
                        System.out.println("Invalid Ingredient Code !!!\n"
                                + "Please ReEnter!!!");
                    }
                } while (!ingredientService.checkID(st, (ArrayList<Ingredients>) x.getIngredients()));
                for (Ingredients a : x.getIngredients()) {
                    if (a.getCodeIngredient().equalsIgnoreCase(st)) {
                        kt = false;
                        System.out.println("Infor Ingredient Before Update:\n "
                                + "-------------------------------------\n"
                                + "| Code|           Name|Quantity| Unit|\n" + a + "\n"
                                + "-------------------------------------\n");
                        st = Tool.inputString("Enter New Ingredient Name := ");
                        if (!st.isEmpty() && !st.trim().isEmpty()) {
                            a.setNameIngredient(st);
                        }
                        do {
                            st = Tool.inputString("Enter Blank To Not Update\n"
                                    + "Or Enter New Quantity := ");
                            if (!st.matches("^\\d+$") && !st.isEmpty() && !st.trim().isEmpty()) {
                                System.out.println("Enter Blank To Not Update\n"
                                        + "Or Quantity Must Be Number And >0!!");
                            }
                        } while (!st.matches("^\\d+$") && !st.isEmpty() && !st.trim().isEmpty());
                        if (!st.isEmpty() && !st.trim().isEmpty()) {
                            a.setQuantityIngredient(st);
                        }
                        do {
                            st = Tool.inputString("Enter Blank To Not Update\n"
                                    + "Or Enter New Unit [g,ml] := ");
                            if (!st.matches("^(g|ml)$") && !st.isEmpty() && !st.trim().isEmpty()) {
                                System.out.println("Unit Must Be 'g' or 'ml'");
                            }
                        } while (!st.matches("^(g|ml)$") && !st.isEmpty() && !st.trim().isEmpty());
                        if (!st.isEmpty() && !st.trim().isEmpty()) {
                            a.setUnitIngredient(st);
                        }
                        System.out.println("Results Of Update Ingredient := \n");
                        System.out.println("-------Ingredient Information--------");
                        System.out.println("| Code|           Name|Quantity| Unit|");
                        System.out.println(a);
                    }
                }
                if (kt) {
                    System.out.println("Invalid Ingredient!!");
                }
                s = Inputter.getString("Do You Want To Continue To Update Ingredients Of Drink??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
            } while (s.equalsIgnoreCase("y"));
            pick = Inputter.getString("Do You Want To Continue To Update Drinks??? \n"
                    + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
        } while (pick.equalsIgnoreCase("y"));
    }

    public void deleteDrink() {
        Menu x = new Menu();
        String st = "";
        System.out.println("There Are Available Drink:");
        showDrink();
        do {
            st = (Inputter.getString("Enter Drink Code Start With 'D' To Delete := ", "Drink Code Must Start With 'D'\n"
                    + "Example D01,D02,DXX..", "^D\\d.*"));
            if (!listDrink.containsKey(st)) {
                System.out.println("Invalid Drink Code !!!\n"
                        + "Please ReEnter!!!");
            }
        } while (!listDrink.containsKey(st));

        x = listDrink.get(st);
        System.out.println("This Is The Drink You Want To Delete!!");
        if (listDrink.isEmpty()) {
            System.out.println("List Drinks Is Emty!!!!");
        } else {
            System.out.println("--------------Drink Information----------------");
            System.out.println("| Code|      Name     |   Beverage Recipes    |");
            System.out.print(x);
            for (Ingredients j : x.getIngredients()) {
                System.out.print(j.getQuantityIngredient() + ":" + j.getNameIngredient() + ",");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
        st = Inputter.getString("Do You Really Want To Delete This Drink??? \n"
                + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
        if (st.equalsIgnoreCase("y")) {
            listDrink.remove(x.getCodeMenu());
            System.out.println("Deleted!!!");
        } else {
            System.out.println("You Canceled!!!");
        }
    }

    public void showDrink() {
        if (listDrink.isEmpty()) {
            System.out.println("List Drinks Is Emty!!!!");
        } else {
            System.out.println("-------------Drink Information----------------");
            System.out.println("| Code|      Name     |   Beverage Recipes    |");
            for (Menu a : SortByName(listDrink).values()) {
                System.out.print(a);
                for (Ingredients x : a.getIngredients()) {
                    System.out.print(x.getQuantityIngredient() + ":" + x.getNameIngredient() + ",");
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------");
        }
    }

    public void saveData(String file) {
        FileOutputStream fos = null;
        try {
            File f = new File(file);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Menu x : listDrink.values()) {
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
                Menu y = (Menu) ois.readObject();
                listDrink.put(y.getCodeMenu(), y);
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

    public HashMap<String, Menu> SortByName(HashMap<String, Menu> ds) {
        List<Map.Entry<String, Menu>> entryList = new ArrayList<>(ds.entrySet());

        // Sử dụng Comparator để sắp xếp theo tên
        entryList.sort(Comparator.comparing(Map.Entry::getKey));

        // Tạo một HashMap mới để lưu trữ kết quả đã sắp xếp
        HashMap<String, Menu> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Menu> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
