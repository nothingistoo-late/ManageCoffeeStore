/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CoffeeStore.Ingredients;
import Management.Management;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import tool.Inputter;
import tool.Tool;

/**
 *
 * @author ntphu
 */
public class ingredientService {

    public static ArrayList<Ingredients> listIngredients = new ArrayList<>();

    public void addIngredient() {
        showIngredient();
        String s = "Y";
        do {
            Ingredients x = new Ingredients();
            do {
                x.setCodeIngredient(Inputter.getString("Enter Ingredient Code Start With 'I' := ", "Ingredient Code Must Start With I\n"
                        + "Example I01,I02,IXX..", "^I\\d.*"));
                if (checkID(x.getCodeIngredient(), listIngredients)) {
                    System.out.println("Valid Ingredient Code !!!\n"
                            + "Please ReInput!!!");
                }
            } while (checkID(x.getCodeIngredient(), listIngredients));
            x.setNameIngredient(Inputter.getString("Enter Ingredient Name := ", "Ingredient Name Cant Be Emty!!"));
            x.setQuantityIngredient(Inputter.getString("Enter Quantity Ingredient := ", "Quantity Must Be Number And >0!!", "^[1-9]\\d*$"));
            x.setUnitIngredient(Inputter.getString("Enter Unit [g,ml]:= ", "Unit Must Be 'g' or 'ml'", "^(g|ml)$"));
            listIngredients.add(x);
            System.out.println("Add Successfull!!");
            s = Inputter.getString("Do You Want To Continue Add New Ingredient??? \n"
                    + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");
        } while (s.equalsIgnoreCase("Y"));
    }

    public static boolean checkID(String codeIngredient, ArrayList<Ingredients> listIngredients) {

        if (listIngredients != null && !listIngredients.isEmpty()) {
            for (Ingredients a : listIngredients) {
                if (a.getCodeIngredient().equalsIgnoreCase(codeIngredient)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public void updateIngredient() {
        boolean kt = true;
        showIngredient();
        String code = Inputter.getString("Enter Ingredient Code Start With 'I':= ", "Ingredient Code Must Start With I\n"
                + "Example I01,I02,IXX..", "^I\\d.*");
        for (Ingredients a : listIngredients) {
            if (a.getCodeIngredient().equalsIgnoreCase(code)) {
                kt = false;
                System.out.println("Infor Ingredient Before Update:\n "
                        + "-------------------------------------\n"
                        + "| Code|           Name|Quantity| Unit|\n" + a + "\n"
                        + "-------------------------------------\n");
                String st = "";
                st = Tool.inputString("Enter New Ingredient Name := ");
                if (!st.isEmpty() && !st.trim().isEmpty()) {
                    a.setNameIngredient(st);
                }

                do {
                    st = Tool.inputString("Enter Blank To Not Update\n"
                            + "Or Enter New Quantity := ");
                    if (!st.matches("^\\d+$") && !st.isEmpty() && !st.trim().isEmpty()) {
                        System.out.println("Quantity Must Be Number And >-0!!");
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
                System.out.println("Results Of Update := \n");
                System.out.println("-------Ingredient Information--------");
                System.out.println("| Code|           Name|Quantity| Unit|");
                System.out.println(a);
            }
        }
        if (kt) {
            System.out.println("Invalid Ingredient!!");
        }
    }

    public void deleteIngredient() {
        String pick = "";
        showIngredient();
        String code = Inputter.getString("Enter Ingredient Code Start With 'I':= ", "Ingredient Must Start With I\n"
                + "Example I01,I02,IXX..", "^I\\d.*");

        if (listIngredients.isEmpty()) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        boolean ingredientFound = false;

        Iterator<Ingredients> iterator = listIngredients.iterator();
        while (iterator.hasNext()) {
            Ingredients a = iterator.next();
            if (a.getCodeIngredient().equalsIgnoreCase(code)) {
                ingredientFound = true;
                pick = Inputter.getString("Do You Really Want To Delete??? \n"
                        + "Enter Your Choice [Y/N] := ", "Input Must Be 'Y' or 'N'!!", "^[NnYy]$");

                if (pick.equalsIgnoreCase("Y")) {
                    // Sử dụng Iterator để xóa phần tử
                    iterator.remove();
                    System.out.println("Deleted!!!");
                } else {
                    System.out.println("You Cancelled!!");
                }
                break; // Nếu tìm thấy và xóa, thoát khỏi vòng lặp
            }
        }

        // Nếu không tìm thấy mã nguyên liệu
        if (!ingredientFound) {
            System.out.println("Invalid Ingredient!!!");
        }
    }

    public ArrayList<Ingredients> sortByName(ArrayList<Ingredients> ds) {
        Collections.sort(ds, (h1, h2) -> h1.getNameIngredient().compareToIgnoreCase(h2.getNameIngredient()));
        return ds;
    }

    public void showIngredient() {
        if (listIngredients.isEmpty()) {
            System.out.println("List Ingredient Is Emty !!!!");
        } else {
            System.out.println("-------Ingredient Information--------");
            System.out.println("| Code|           Name|Quantity| Unit|");
            for (Ingredients a : sortByName(listIngredients)) {
                System.out.println(a);
            }
            System.out.println("-------------------------------------");
        }
    }

    public void ingredientAvailable() {
        ArrayList<Ingredients> available = new ArrayList<>();
        for (Ingredients a : listIngredients) {
            if (a.getQuantityIngredient().matches("^[0-9]*[1-9][0-9]*$")) {
                available.add(a);
            }
        }
        if (available.isEmpty()) {
            System.out.println("List Ingredient Is Emty!!!!");
        } else {
            System.out.println("-------Ingredient Information--------\n");
            System.out.println("| Code|           Name|Quantity| Unit|\n");
            for (Ingredients a : sortByName(available)) {
                System.out.println(a);
            }
            System.out.println("-------------------------------------\n");
        }
    }

    public void saveData(String file) {
        FileOutputStream fos = null;
        try {
            File f = new File(file);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Ingredients x : listIngredients) {
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
                Ingredients y = (Ingredients) ois.readObject();
                listIngredients.add(y);
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
