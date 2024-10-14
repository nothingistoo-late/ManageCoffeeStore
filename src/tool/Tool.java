/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ntphu
 */
public class Tool {
        public static String inputString(String mess){
            String res = "";
            Scanner sc = new Scanner(System.in);
            System.out.print(mess);
            res = sc.nextLine();
            return res;
        }
        
        public static int inputInt(String mess){
            int res = 0;
            String s = inputString(mess);
            res = Integer.parseInt(s);
            return res;
        }
        
        public static double inputDouble(String mess){
            double res = 0;
            String tam = inputString(mess);
            res = Double.parseDouble(tam);
            return res;
        }
        public static float inputFloat(String mess){
            float res = 0;
            String tam = inputString(mess);
            res = Float.parseFloat(tam);
            return res;
        }
        public static boolean inputBoolean(String mess){
            boolean res;
            int k = inputInt(mess);
            res = k==1;
            return res;
        }
        
        public static Date inputDate(String mess) throws ParseException{
            Date res = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yy");
            String tam = inputString(mess);
            res = sf.parse(tam);
            return res;
        }
    
}
