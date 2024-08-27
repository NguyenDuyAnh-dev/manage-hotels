/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.File;

/**
 *
 * @author PHUCHAU
 */
public interface I_List {
    void add();
    void loadForm(File file);
    String checkIDExits(File file);
    void updateInfo(File file);
    void deleteByID(File file);
    void searchHotelByID();
    void searchNameHotel();
    void showList();
}
