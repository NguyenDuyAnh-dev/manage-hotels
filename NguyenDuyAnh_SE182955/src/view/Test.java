/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.HotelList;
import controllers.Menu;
import dto.Hotel;
import java.io.File;

/**
 *
 * @author PHUCHAU
 */
public class Test {
    public static void main(String[] args) {
    
        File file = new File("Hotel.dat");
        Menu menu = new Menu();
        menu.addItem("==========Menu================");
        menu.addItem("0. Add new hotel");
        menu.addItem("1. Checking exists Hotel");
        menu.addItem("2. Updating Hotel");
        menu.addItem("3. Deleting Hotel");
        menu.addItem("4. Searching Hotel");
        menu.addItem("5. Displaying Hotel list");
        menu.addItem("=============================");
        int choice;
        HotelList list = new HotelList();
        Hotel hotel1 = new Hotel("H01", "Seraton", 10, "189 Ung van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh city", "0911796099", 4);
        Hotel hotel2 = new Hotel("H02", "VinStar", 5, "200 Ung van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh city", "0918940111", 5);
        Hotel hotel3 = new Hotel("H03", "OutString", 7, "300 D1, Ward 24, Binh Thanh District, Ho Chi Minh city", "0988940222", 6);
        Hotel hotel4 = new Hotel("H04", "Betigar",8, "189 Duong Quang Ham, Ward 5, Do Vap, Ho Chi Minh city", "0977940100", 3);
        list.add(hotel1);
        list.add(hotel2);
        list.add(hotel3);
        list.add(hotel4);
        list.loadForm(file);
        do{
            menu.showMenu();
            choice = menu.getChoice();
            switch(choice){
                case 0:
                    do{
                        list.add();
                        list.saveFile(file);
                    }while(!menu.confirmYesNo("Do you want to exit to menu? (Y/N)"));
                    
                    break;
                
                case 1:
                    
                    do{
                        System.out.println(list.checkIDExits(file));
                    }while(!menu.confirmYesNo("Do you want to exit to menu? (Y/N)"));
                    break;
                    
                case 2:
                    list.updateInfo(file);
                    break;
                    
                case 3:
                    list.deleteByID(file);
                    break;
                    
                case 4:
                    Menu subMenu = new Menu();
                    menu.addItem("=== Searching vehicle ====");
                    subMenu.addItem("1.Search by Name_Vehicle");
                    subMenu.addItem("2.Search by ID_Vehicle");
                   subMenu.addItem("Others: Thoat");
                    int choiceSub;
                    do{  
                        subMenu.showMenu();
                          choiceSub= subMenu.getChoice();
                    switch(choiceSub){
                        case 1:
                            list.searchNameHotel();
                            break;
                        case 2: 
                            list.searchHotelByID();
                            break;
                        default:
                            System.out.println("Thoat");
                    }
                    }while(choiceSub==1||choiceSub==2);
                    break;
                    
                case 5:
                    list.showList();
                    break;
                    
                default:
                    list.saveFile(file);
                        System.out.println("Thoat.");
            }
        }while(choice >= 0 && choice < 6);
    }
}
