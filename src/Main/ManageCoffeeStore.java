/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CoffeeStore.Ingredients;
import Management.Management;
import java.util.ArrayList;
import tool.Inputter;

/**
 *
 * @author ntphu
 */
public class ManageCoffeeStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Management run = new Management();
        run.loadData();
        String managementMenu = "-------Coffee Store-------\n"
                + "1 - Manage ingredients\n"
                + "2 - Manage beverage recipes\n"
                + "3 - Dispensing beverages\n"
                + "4 - Report\n"
                + "5 - Save Data\n"
                + "6 - Exit.";
        int choice = 0;
        do {
            System.out.println(managementMenu);
            choice = Inputter.getAnInteger("Enter Your Choice := ", "Choice must be 1=>choice<=6", 1, 6);
            switch (choice) {
                case 1:
                    run.ingredientManagement();
                    break;
                case 2:
                    run.menuManagement();
                    break;
                case 3:
                    run.orderManagement();
                    break;
                case 4:
                    run.report();
                    break;
                case 5:
                    run.saveData();
                    System.out.println("Saved!!!");
                    break;
                case 6:
                    System.out.println("You Exit....");
                    break;
                default:
                    System.out.println("Error...");
            }
        } while (choice != 6);
    }

}
