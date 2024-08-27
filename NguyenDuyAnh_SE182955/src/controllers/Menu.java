/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import ultis.Utils;

/**
 *
 * @author PHUCHAU
 */
public class Menu extends ArrayList<String>{

    // constructor
    public Menu() {
        super();
    }
    
    // nhap hien thi cho menu
    public void addItem(String s){
        this.add(s);
    }
    
    // hien thi menu
    public void showMenu(){
        for(String menu : this){
            System.out.println(menu.toString());
        }
    }
    
    // nhap yes no
    public boolean confirmYesNo(String welcome){
        boolean ressult = Utils.confirmYesNo(welcome);
        return ressult;
    }
    
    // nhap lua chon
    public int getChoice(){
        int choice = Utils.getInt("Choice one work: ");
        return choice;
    }
    
    
}
