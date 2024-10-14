
package tool;
//Inputter là 1 bộ công cụ giúp mình nhập bất cứ thứ gì
//cần nhập số có inputter

import java.util.Scanner;

//nhập chữ có Inputter
//nhập số trong khoảng có Inputter
//nhập tào lao là chửi được, ép nhập lại

public class Inputter {
    //nhập số nguyên: cấm nhập tào lao
    public static Scanner sc = new Scanner(System.in);
    
    public static int getAnInteger(String inpMsg, String errMsg){
        System.out.print(inpMsg);
        int number;
        while(true){
            try{
                number = Integer.parseInt(sc.nextLine());
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
                System.out.print(inpMsg);
            }
            
        }
    }
    
    //method nhập số nguyên trong khoảng
    public static int getAnInteger(String inpMsg, String errMsg, int lowerBound,
            int upperBound){
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.print(inpMsg);
        int number;
        while(true){
            try{
                number = Integer.parseInt(sc.nextLine());
                if(number > upperBound || number < lowerBound){
                    throw new Exception();
                }
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
                System.out.print(inpMsg);
            }
            
        }
        
    }
    //hàm nhập số thực
    
    public static double getADouble(String inpMsg, String errMsg){
        System.out.print(inpMsg);
        double number;
        while(true){
            try{
                number = Double.parseDouble(sc.nextLine());
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
                System.out.print(inpMsg);
            }
        }
    }
    //hàm nhập số thực trong khoảng
    public static double getADouble(String inpMsg, String errMsg, double lowerBound,
            double upperBound){
        if(lowerBound > upperBound){
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
         System.out.print(inpMsg);
        double number;
        while(true){
            try{
                number = Double.parseDouble(sc.nextLine());
                if(number > upperBound || number < lowerBound){
                    throw new Exception();
                }
                return number;
            }catch(Exception e){
                System.out.println(errMsg);
                 System.out.print(inpMsg);
            }
            
        }
        
    }
    
    //hàm nhập chuỗi cấm để trống
    public static String getString(String inpMsg, String errMsg){
        System.out.print(inpMsg);
        while(true){
            try{
                String str = sc.nextLine();
                if(str.isEmpty() || str.equals("") || str.matches("\\s*")){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg); 
                System.out.print(inpMsg);
            }
        }
    }
    
    public static String getString(String inpMsg, String errMsg, String regex){
        System.out.print(inpMsg);
        while(true){
            try{
                String str = sc.nextLine();
                if(str.isEmpty() || str.trim().equals("") || !str.matches(regex)){
                    if (str.isEmpty()||str.trim().equals(""))
                        System.out.println("Input Cant Be Emty!!!");
                    else if (!str.matches(regex))
                        System.out.println(errMsg);
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                 System.out.print(inpMsg);
            }
            
        }
        
    }

}

