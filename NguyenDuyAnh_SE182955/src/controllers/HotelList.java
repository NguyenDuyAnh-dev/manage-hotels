/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import dto.Hotel;
import dto.I_List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import ultis.Utils;
/**
 *
 * @author PHUCHAU
 */
public class HotelList extends ArrayList<Hotel> implements I_List{

//constructor
    public HotelList() {
        super();
    }

//constructor
// them hotel moi
    @Override
    public void add() {
        try {
            String Hotel_id = inputID("Input ID: ");
            String Hotel_Name = Utils.getString("Input name: ");
            int Hotel_Room_Available = Utils.getIntGreaterThanZero("Input number of room: ");
            String Hotel_Address = inputAddress("Input address: ");
            String Hotel_Phone = inputPhone("Input phone number: ");
            int Hotel_Rating = Utils.getRating("Input rating: ");
            Hotel hotel = new Hotel(Hotel_id, Hotel_Name, Hotel_Room_Available, Hotel_Address, Hotel_Phone, Hotel_Rating);

            // them vao danh sach
            this.add(hotel);
            
            System.out.println("add success!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("add fail");
        }
    }

// bat dau method kiem tra trung lap khong 
    // kiem tra ID bang index(kiem tra co ton tai hay ko)
    public int checkHotelID(String code) {
        int check = -1;
        for (Hotel hotel : this) {
            if (hotel.getHotel_id().equals(code)) {
                check = this.indexOf(hotel);
                break;
            }
        }
        return check;
    }

    // nhap ID vao hotel moi
    public String inputID(String message) throws Exception {// tranh nhap ki tu bi trung
        String result = Utils.getString(message);
        if (checkHotelID(result) != -1) {// kiem tra xem co trung index cua id nao ko
            throw new Exception("ID must be unique!!!");
        }
        return result;
    }

    // kiem tra Address bang index(kiem tra co ton tai hay ko)
    public int checkHotelAddress(String code) {
        int check = -1;
        for (Hotel hotel : this) {
            if (hotel.getHotel_Address().equals(code)) {
                check = this.indexOf(hotel);
                break;
            }
        }
        return check;
    }

    // nhap address cho hotel moi
    public String inputAddress(String message) throws Exception {// tranh nhap ki tu khac
        String result = Utils.getString(message);
        if (checkHotelAddress(result) != -1) {
            throw new Exception("Address must be unique!!!");
        }
        return result;
    }

    // kiem tra phone number bang index( kiem tra co ton tai hay ko)
    public int checkPhone(String longNumber) {
        int check = -1;
        for (Hotel hotel : this) {
            if (hotel.getHotel_Phone().equals(longNumber)) {
                check = this.indexOf(hotel);
                break;
            }
        }
        return check;
    }

    // nhap so dien thoai moi
    public String inputPhone(String message) throws Exception {
        String result = Utils.getPhoneNumberFormat(message);
        if (checkPhone(result) != -1) {
            throw new Exception("phone must be unique!!!");
        }
        return result;
    }

// method kiem tra trung lap khong 
// luu file
    public void saveFile(File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (Hotel hotel : this) {
                oos.writeObject(hotel);
            }
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// luu file

//// kiem tra trong file 
////    //doc doi tuong
    @Override
    public void loadForm(File file) {
        try {
           
            FileInputStream fis = new FileInputStream(file);
            // đọc
            ObjectInputStream ois = new ObjectInputStream(fis);
             while(true){
                 try{
                     Hotel hotel = (Hotel)ois.readObject();
                     this.add(hotel);
                 }catch(Exception e){
                     break;
                 }
             }
            ois.close();

        } catch (Exception e) {
            System.out.println("Fail to read file: " + file);
        }
    }
    
    //kiem tra ID trong file
    @Override
    public String checkIDExits(File file){
        String text = Utils.getString("Input ID:");
        this.loadForm(file);
        for(Hotel hotel : this){
            if(hotel.getHotel_id().equals(text)){
                return "Hotel Exits";
            }
        }
        return "Hotel not exits";
    }
//// kiem tra trong file 
    
// update info
    
    // update address
    public String upAddress(String message, String code) throws Exception{
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        for(Hotel hotel : this){
            if(hotel.getHotel_Address().equals(result) && this.indexOf(hotel) != checkHotelAddress(code)){
                throw new Exception("Error update ( the address must be unique ) !!");
            }
        }
        if(result.isEmpty()){
            return code;
        }
        return result;
    }
    
    //update phone number
    public String updatephonNumber(String message, String code) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        String result = sc.nextLine();
        for(Hotel hotel : this){
            if(hotel.getHotel_Phone().equals(result) && this.indexOf(hotel) != checkPhone(code)){
                throw new Exception("Error update ( the phone number must be unique ) !!");
            }
        }
        if(result.isEmpty()){
            return code;
        }
        return result;
    }
    
    @Override
    public void updateInfo(File file){
        this.clear();
        String info = Utils.getString("Input ID: ");
        loadForm(file);
        if(checkHotelID(info) == -1){ // liem tra co ton tai hay ko
            System.out.println("hotel does not exits");
        }else{// neu ton tai
            try{
                Hotel hotel = this.get(checkHotelID(info));
                
                hotel.setHotel_Name(Utils.getString("Input Name: ", hotel.getHotel_Name()));
                hotel.setHotel_Room_Available(Utils.getIntGreaterThanZero("Input quantity Room: ", hotel.getHotel_Room_Available()));
                hotel.setHotel_Address(upAddress("Input address: ", hotel.getHotel_Address()));
                hotel.setHotel_Phone(updatephonNumber("Input phone number: ", hotel.getHotel_Phone()));
                hotel.setHotel_Rating(Utils.getRating("Input rating: ", hotel.getHotel_Rating()));
                
                saveFile(file);
                System.out.println("Updated success!!!");
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("updated failse");
            }
        }
    }
    
    
// update info

//delete hotel by ID
    @Override
    public void deleteByID(File file){
        this.clear();
        loadForm(file);//doc file
        
        for(Hotel hotel : this){
            System.out.println(hotel.toString());
        }
        String result = Utils.getString("Input ID: ");
        
        
        if(checkHotelID(result) == -1){
            System.out.println("Fail!! ");
        }
    
        for(int i = 0; i < this.size(); i++){
            Hotel hotel = this.get(i);
            if(hotel.getHotel_id().equals(result)){
                if(Utils.confirmYesNo("Do you ready want to delete this hotel")){
                    this.remove(hotel);
                }
            }
        }
        
        
        System.out.println("Success!!!");
    }
//delete hotel by ID

// search Hotel By ID
    @Override
    public void searchHotelByID(){
        File file = new File("Hotel.dat");
        String idInput = Utils.getString("Input ID for search: ");
        this.clear();
        loadForm(file);
        int count = 0;
        
        //so sanh va sap xep du lieu
        Collections.sort(this, new Comparator<Hotel>(){
            @Override
            public int compare(Hotel o1, Hotel o2) {
                if(o1.getHotel_id().compareTo(o2.getHotel_id()) < 0){
                    return 1;
                }else if(o1.getHotel_id().compareTo(o2.getHotel_id()) == 0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        
        // in ra danh sach giam dan
        
        for(Hotel hotel : this){
            if(hotel.getHotel_id().toLowerCase().equals(idInput.toLowerCase())){
                System.out.println(hotel.toString());
                System.out.println("=====================================");
                count++;
            }
        }
        if(count == 0){// ko tim thay du lieu ma ng dung nhap vao
            System.out.println("No Hotel have that ID exits!!!!");
        }
        
    }
// search Hotel By ID
    
//seaerch by Name
    @Override
    public void searchNameHotel(){
        File file = new File("Hotel.dat");
        this.clear();
        loadForm(file);
        String nameInput = Utils.getString("Input name for search: ");
        int count = 0;
        
        //so sanh va xap xep du lieu
        Collections.sort(this, new Comparator<Hotel>(){
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o1.getHotel_Name().compareTo(o2.getHotel_Name());
            }

            @Override
            public Comparator<Hotel> reversed() {
                return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        // in ra danh sach giam dan
         for(Hotel hotel : this){
             if(hotel.getHotel_Name().toLowerCase().contains(nameInput.toLowerCase())){
                 System.out.println(hotel);
                 count++;
             }
         }
        if(count == 0){
            System.out.println("there is no name in list: ");
        }
    }
//seaerch by Name  

// show list Hotel
    @Override
    public void showList(){
        File file = new File("Hotel.dat");
        this.clear();
        loadForm(file);
        for(Hotel hotel : this){
            System.out.println(hotel.toString());
        }
        
        System.out.println("Descending by hotel_name: ");
        
        Collections.sort(this, new Comparator<Hotel>(){
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o1.getHotel_Name().compareTo(o2.getHotel_Name());
            }

            @Override
            public Comparator<Hotel> reversed() {
                return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        for(Hotel hotel : this){
            System.out.println(hotel.toString());
        }
        
    }
// show list Hotel
}
