/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author MATHIS
 */
public class ServiceUtils {
    
    public static String createCode(){
        Random r = new Random();
        int m = r.nextInt(45); 
        int a = r.nextInt(10);
        int o = r.nextInt(20);
        return ""+m+a+o;
    }
    
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
