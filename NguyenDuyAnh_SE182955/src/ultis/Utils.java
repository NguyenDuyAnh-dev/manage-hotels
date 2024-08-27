/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultis;

import java.util.Scanner;

/**
 *
 * @author PHUCHAU
 */
public class Utils {
    
    // ko cho nguoi dung bo trong khi nhap text
    public static String getString(String welcome){ 
        String result = null;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println(welcome);
            result = sc.nextLine();
            if(!result.isEmpty()){
                break;
            }
        }while(true);
        return result;
    }
    
    // cap nhat String (neu ko co su dung data cu)
    public static String getString(String welcome, String oldData){
        String result = oldData;
        
            Scanner sc = new Scanner(System.in);
            System.out.println(welcome);
            
            String tmp = sc.nextLine();
            if(!tmp.isEmpty()){
                result = tmp;
            }
        
        return result;
    }
    
    // ko cho nguoi dung nhap so nho hon 0
    public static int getIntGreaterThanZero(String welcome){
        int num = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println(welcome);
                num = Integer.parseInt(sc.nextLine());
                if(num >= 0){
                    break;
                }
            }catch(Exception e){
                System.out.println("input integer!!!");
            }
        }while(true);
        return num;
    }
    
    //cap nhat nhap so lon hon 0
    public static int getIntGreaterThanZero(String welcome, int oldData){
        int number = 0;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(welcome);
        
            number = Integer.parseInt(sc.nextLine());
            if(number < 0){
                number = oldData;// nhap be hon 0 se lay data cu
            }
        }catch(Exception e){
            number = oldData;// ko nhap thi se lai data cu
        }
        return number;
    }
    
    // ko cho nguoi dung nhap sai cu phap cua so dien thoai
    public static String getPhoneNumberFormat(String welcome){
        String number = "";
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println(welcome);
                number = sc.nextLine();
                if(number.matches("^0\\d{9}$")){
                    break;
                }else {
                    System.out.println("Invalid phone number format. Please try again.");
                }
            }catch(Exception e){
                System.out.println("Input the number!!!");
            }
        }while(true);
        return number;
    }
    
    //cap nhat so dien thoai
    public static String getPhoneNumberFormat(String welcome, String oldData){
        String number = oldData;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(welcome);
            
            String tmp = sc.nextLine();
            if(!number.isEmpty()){
                number = tmp;
            }
        }catch(Exception e){
            number = oldData;
        }
        return number;
    }
    
    // ko cho nguoi dung nhap so nho hon 0 va lon hon 5
    public static int getRating(String welcome){
        int number = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println(welcome);
                number = Integer.parseInt(sc.nextLine());
                if(number >= 0 && number <= 6){
                    break;
                }else{
                    System.out.println("Input number >= 0 and <= 6");
                }
            }catch(Exception e){
                System.out.println("Input the number !!!");
            }
        }while(true);
        return number;
    }
    
    //caap nhat rating
    public static int getRating(String welcome, int oldData){
        int number = 0;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(welcome);
            
            number = Integer.parseInt(sc.nextLine());
            if(number < 0 && number > 6){
                number = oldData;
            }
        }catch(Exception e){
            number = oldData;
        }
        return number;
    }
    
    //  tra loi yes no
    //nhap y hoac N
    public static String getYesNo(String welcome){
        String result = null;
        do{
            result = Utils.getString(welcome);
            if(result.equalsIgnoreCase("Y") || result.equalsIgnoreCase("N")){
                break;
            }
        }while(true);
        return result;
    }
    
    // kiem tra Y va N
    public static boolean confirmYesNo(String welcome) {
            boolean result = false;
            
            String confirm=Utils.getYesNo(welcome);
            if ("Y".equalsIgnoreCase(confirm)) {
                result = true; 
            }
    return result;
    }
    
    //nhap so int
    public static int getInt(String welcome){
        int number = 0;
        try{
            System.out.println(welcome);
            Scanner sc = new Scanner(System.in);
            
            number = Integer.parseInt(sc.nextLine());
            
        }catch(Exception e){
            // ko cho bo trong
            System.out.println("Input the integer!!!");
        }
        return number;
    }
}
